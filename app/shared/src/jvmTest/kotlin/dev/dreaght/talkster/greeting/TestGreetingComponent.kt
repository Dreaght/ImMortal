package dev.dreaght.talkster.greeting

import com.arkivanov.decompose.value.MutableValue

class TestGreetingComponent(model: GreetingComponent.Model) : GreetingComponent {

    override val model: MutableValue<GreetingComponent.Model> = MutableValue(model)

    var isToggleClicked: Boolean = false

    override fun onToggleClicked() {
        isToggleClicked = true
    }
}
