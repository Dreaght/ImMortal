package dev.dreaght.talkster.greeting

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dev.dreaght.talkster.greeting.GreetingComponent.Model

class PreviewGreetingComponent() : GreetingComponent {

    override val model: Value<Model> =
        MutableValue(
            Model(
                showContent = true,
                greetingText = "Hello, World!"
            )
        )

    override fun onToggleClicked() {}
}
