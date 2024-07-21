package app.ai.presentation.test

import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.ai.vectorstore.filter.Filter
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class Test3Controller(
//    private val openAiChatModel: OpenAiChatModel
    @Value("\${key.open-ai}") private val apiKey: String,
    private val vectorStore: VectorStore,
) {

    @GetMapping("/test44")
    fun test(): Any {
        val b = FilterExpressionBuilder()
        val expression: Filter.Expression = FilterExpressionBuilder().eq("page_number", 4).build()

        val similaritySearch = vectorStore.similaritySearch(
            SearchRequest.query("지급 시기")
                .withTopK(3)
                .withFilterExpression(expression)
        )

        return similaritySearch
    }

}