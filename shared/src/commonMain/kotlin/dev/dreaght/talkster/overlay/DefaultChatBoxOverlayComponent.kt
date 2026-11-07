package dev.dreaght.talkster.overlay

import com.arkivanov.decompose.ComponentContext

class DefaultChatBoxOverlayComponent(
    componentContext: ComponentContext
) : ChatBoxOverlayComponent, ComponentContext by componentContext {

}
