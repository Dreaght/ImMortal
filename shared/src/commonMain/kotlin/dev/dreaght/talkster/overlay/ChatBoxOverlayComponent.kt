package dev.dreaght.talkster.overlay

import com.arkivanov.decompose.ComponentContext

interface ChatBoxOverlayComponent {

}

class DefaultChatBoxOverlayComponent(
    componentContext: ComponentContext
) : ChatBoxOverlayComponent, ComponentContext by componentContext {

}
