package dev.dreaght.talkster.greeting

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import dev.dreaght.talkster.Platform
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Suppress("TestFunctionName")
class GreetingComponentIntegrationTest {

    private val testPlatform = object : Platform {
        override val name: String
            get() = "Test Platform"
    }
    private val lifecycle = LifecycleRegistry()

    private val component =
        DefaultGreetingComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            platform = testPlatform,
        )

    private val model get() = component.model.value

    @BeforeTest
    fun before() {
        lifecycle.resume()
    }

    @Test
    fun WHEN_created_THEN_platform_correct() {
        assertEquals("Hello, Test Platform!", model.greetingText)
    }

    @Test
    fun WHEN_created_THEN_showContent_true() {
        assertFalse(model.showContent)
    }

    @Test
    fun WHEN_onToggleClicked_THEN_showContent_true() {
        assertFalse(model.showContent)

        component.onToggleClicked()
        assertTrue(model.showContent)
    }

    @Test
    fun WHEN_onToggleClicked_again_THEN_showContent_false() {
        assertFalse(model.showContent)

        component.onToggleClicked()
        assertTrue(model.showContent)

        component.onToggleClicked()
        assertFalse(model.showContent)
    }
}
