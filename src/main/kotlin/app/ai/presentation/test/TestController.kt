package app.ai.presentation.test

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(
//    private val openAiChatModel: OpenAiChatModel
    @Value("\${key.open-ai}") private val apiKey : String,
) {

    @GetMapping("/test")
    fun test(): ChatResponse {

        val openAiChatModel = OpenAiChatModel(
            OpenAiApi(apiKey),
            OpenAiChatOptions.builder().withModel("gpt-4o").build()
        )

        val builder = ChatClient.builder(openAiChatModel).build()

        val chatResponse = builder.prompt()
            .user("Hello, how are you?")
            .call()
            .chatResponse()

        return chatResponse
    }


}