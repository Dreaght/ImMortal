package dev.dreaght.talkster.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.dreaght.talkster.greeting.GreetingComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class GreetingScreen(val component: GreetingComponent) : Child()
    }
}
