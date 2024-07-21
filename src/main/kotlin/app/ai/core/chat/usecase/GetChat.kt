package app.ai.core.chat.usecase

import app.ai.core.UseCase
import app.ai.core.chat.service.ChatService
import org.springframework.stereotype.Component

@Component
class GetChat(
    private val chatService: ChatService,
) : UseCase<GetChat.Input, String> {

    data class Input(
        val prompt: String,
        val query: String,
        val ragId: Long
    )

    override fun execute(input: Input): String = chatService.chat(
        input.prompt,
        input.query,
        input.ragId
    )

}