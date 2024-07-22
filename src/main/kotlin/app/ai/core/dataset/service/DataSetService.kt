package app.ai.core.dataset.service

import app.ai.infrastructure.vector.VectorRepository
import org.springframework.ai.document.Document
import org.springframework.stereotype.Service

@Service
class DataSetService(
    private val vectorRepository: VectorRepository
) {

    fun create(list : List<Document>) = vectorRepository.accept(list)

}