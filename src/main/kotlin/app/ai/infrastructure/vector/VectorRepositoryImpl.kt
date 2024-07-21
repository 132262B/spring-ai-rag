package app.ai.infrastructure.vector

import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.stereotype.Repository

@Repository
class VectorRepositoryImpl(
    private val vectorStore: VectorStore,
) : VectorRepository {

    override fun accept(documents: List<Document>) = vectorStore.add(documents)


}