package dev.dreaght.talkster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import dev.dreaght.talkster.root.DefaultRootComponent
import dev.dreaght.talkster.root.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val root =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
                platform = AndroidPlatform()
            )

        setContent {
            RootContent(root)
        }
    }
}
