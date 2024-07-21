package app.ai.infrastructure.vector

import org.springframework.ai.document.Document

interface VectorRepository{

    fun accept(documents : List<Document>)
}