package app.ai.core.chat.service

import app.ai.infrastructure.openai.OpenAiRepository
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(
    private val openAiRepository: OpenAiRepository,
) : ChatService {

    override fun chat(
        prompt: String,
        query: String,
        ragId: Long
    ): String = openAiRepository.chat(prompt, query, ragId)

}