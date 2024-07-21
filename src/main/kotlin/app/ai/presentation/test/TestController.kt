package app.ai.presentation.test

import co.elastic.clients.elasticsearch.core.SearchResponse
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.document.Document
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.ai.vectorstore.ElasticsearchVectorStore
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(
//    private val openAiChatModel: OpenAiChatModel
    @Value("\${key.open-ai}") private val apiKey: String,
    private val vectorStore: VectorStore,
) {

    @GetMapping("/test")
    fun test(): ChatResponse {
        val openAiChatModel = OpenAiChatModel(
            OpenAiApi(apiKey),
            OpenAiChatOptions.builder().withModel("gpt-4o").build()
        )

        val builder = ChatClient.builder(openAiChatModel)
            .build()

        val chatResponse = builder.prompt()
            .user("""
                너는 약관을 설명하는 봇이야.
                
                지급 시기에 대해 알려줘
                그게 어떤 문서, 몇페이지를 봐야하는지도 알려줘.
            """.trimIndent())
            .advisors(QuestionAnswerAdvisor(vectorStore, SearchRequest.query("지급 시기에 대해 알려줘").withTopK(1)))
            .call()
            .chatResponse()

        return chatResponse
    }

}