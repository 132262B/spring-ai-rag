package app.ai.infrastructure.openai

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class OpenAiRepositoryImpl(
    @Value("\${key.open-ai}") private val apiKey: String,
    private val vectorStore: VectorStore,
) : OpenAiRepository {

    override fun chat(prompt: String, query: String, ragId: Long): String {

        val openAiChatModel = OpenAiChatModel(
            OpenAiApi(apiKey),
            OpenAiChatOptions.builder().withModel("gpt-4o").build()
        )

        val builder = ChatClient.builder(openAiChatModel)
            .build()

        val chatResponse = builder.prompt()
            .system(prompt.trimIndent())
            .user(query)
            .advisors(
                QuestionAnswerAdvisor(
                    vectorStore,
                    SearchRequest
                        .query(query)
                        .withTopK(1)
                        .withFilterExpression(FilterExpressionBuilder().eq("rag_id", ragId).build())
                )
            )
            .call()
            .chatResponse()
            .result.output.content

        return chatResponse
    }


}