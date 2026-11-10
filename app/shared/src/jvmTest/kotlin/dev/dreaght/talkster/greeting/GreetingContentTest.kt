package dev.dreaght.talkster.greeting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("TestFunctionName")
class GreetingContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun WHEN_shown_THEN_toggleButton_displayed() {
        test {
            composeRule.onNodeWithTag(testTag = "toggleButton").assertIsDisplayed()
        }
    }

    @Test
    fun GIVEN_showContent_false_WHEN_shown_THEN_text_does_not_exist() {
        test(model = GreetingComponent.Model(
            showContent = false,
            greetingText = "Hello, Test Platform!"
        )) {
            composeRule.onNodeWithTag(testTag = "text").assertDoesNotExist()
        }
    }

    @Test
    fun GIVEN_showContent_true_WHEN_shown_THEN_text_displayed() {
        test(model = GreetingComponent.Model(
            showContent = true,
            greetingText = "Hello, Test Platform!"
        )) {
            composeRule.onNodeWithTag(testTag = "text").isDisplayed()
        }
    }

    @Test
    fun GIVEN_showContent_false_WHEN_toggleButton_clicked_THEN_onToggleClicked_called() {
        test(model = GreetingComponent.Model(
            showContent = false,
            greetingText = "Hello, Test Platform!"
        )) { component ->
            composeRule.onNodeWithTag(testTag = "toggleButton").performClick()

            assertTrue(component.isToggleClicked)
        }
    }

    private fun test(
        model: GreetingComponent.Model = GreetingComponent.Model(
            showContent = false,
            greetingText = "Hello, Test Platform!"
        ),
        block: suspend CoroutineScope.(TestGreetingComponent) -> Unit,
    ) {
        runBlocking(Dispatchers.Main) {
            val component = TestGreetingComponent(model = model)
            composeRule.setContent { GreetingContent(component = component) }
            composeRule.awaitIdle()
            block(component)
        }
    }
}
