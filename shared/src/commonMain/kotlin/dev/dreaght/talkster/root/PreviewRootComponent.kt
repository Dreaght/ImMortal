package dev.dreaght.talkster.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dev.dreaght.talkster.greeting.PreviewGreetingComponent

class PreviewRootComponent : RootComponent {
    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        MutableValue(
            ChildStack(
                configuration = Unit,
                instance = RootComponent.Child.GreetingScreen(PreviewGreetingComponent()),
            )
        )
}
