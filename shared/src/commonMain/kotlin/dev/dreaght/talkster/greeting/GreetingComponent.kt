package dev.dreaght.talkster.greeting

import com.arkivanov.decompose.value.Value

interface GreetingComponent {
    val model: Value<Model>

    fun onToggleClicked()

    data class Model(
        val showContent: Boolean,
        val greetingText: String
    )
}
