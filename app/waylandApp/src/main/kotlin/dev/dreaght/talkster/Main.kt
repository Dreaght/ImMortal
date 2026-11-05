package dev.dreaght.talkster

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.DecomposeSettings
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.create
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.fromwau.kern.result.onError
import com.fromwau.kortex.wayland.AppMenu
import com.fromwau.kortex.wayland.Window
import com.fromwau.kortex.wayland.WindowStatus
import com.fromwau.kortex.wayland.kortexApplication
import com.fromwau.kortex.wayland.rememberSurfaceState
import com.fromwau.kortex.wayland.rememberWindowState
import dev.dreaght.talkster.overlay.ChatBoxOverlayContent
import dev.dreaght.talkster.overlay.DefaultChatBoxOverlayComponent
import dev.dreaght.talkster.root.DefaultRootComponent
import dev.dreaght.talkster.root.RootContent
import kotlin.system.exitProcess

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    DecomposeSettings.update { settings ->
        settings.copy(mainThreadCheckEnabled = false)
    }

    val lifecycle = LifecycleRegistry()

    kortexApplication {
        val windowState = rememberWindowState()
        val chatBoxOverlayState = rememberSurfaceState()

        var showing by remember { mutableStateOf(true) }
        val status = windowState.status

        val root =
            DefaultRootComponent(
                componentContext = DefaultComponentContext(lifecycle = lifecycle),
                platform = JVMPlatform()
            )

        val chatBoxOverlay =
            DefaultChatBoxOverlayComponent(
                componentContext = DefaultComponentContext(lifecycle = lifecycle),
            )

        LaunchedEffect(status) {
            when (status) {
                is WindowStatus.Placing -> {
                    lifecycle.create()
                }
                is WindowStatus.OnScreen -> {
                    lifecycle.resume()
                }
                is WindowStatus.Ended -> {
                    lifecycle.destroy()
                }
            }
        }

        if (showing) {
            Window(
                title = "talkster",
                appId = "dev.dreaght.talkster",
                state = windowState
            ) {
                if (windowState.closeRequested) {
                    ConfirmQuit(
                        onKeep = { windowState.declineClose() },
                        onQuit = { showing = false },
                    )
                }
                RootContent(root)
            }

            AppMenu(
                width = 640.dp,
                height = 480.dp,
                state = chatBoxOverlayState
            ) {
                ChatBoxOverlayContent(
                    component = chatBoxOverlay,
                    modifier = Modifier.onPreviewKeyEvent {
                        when {
                            (it.key == Key.Escape && it.type == KeyEventType.KeyUp) -> {
                                close()
                                true
                            }
                            else -> false
                        }
                    }
                )
            }
        }
        LaunchedEffect(status) { if (status is WindowStatus.Ended) exitApplication() }
    }.onError { error ->
        System.err.println("kortex: $error")
        exitProcess(1)
    }
}
