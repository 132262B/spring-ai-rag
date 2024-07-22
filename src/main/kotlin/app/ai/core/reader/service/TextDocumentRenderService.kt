package app.ai.core.reader.service

import org.springframework.ai.document.Document
import org.springframework.ai.reader.TextReader
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import java.io.InputStream


class TextDocumentRenderService : DocumentRenderService {
    override fun render(
        inputStream: InputStream,
        originalFileName: String,
        ragId: Long
    ): List<Document> {
        val inputStreamResource : Resource = InputStreamResource(inputStream, originalFileName)

        val textReader = TextReader(inputStreamResource)
        textReader.customMetadata["file_name"] = originalFileName
        textReader.customMetadata["rag_id"] = ragId

        return textReader.read()
    }


}
