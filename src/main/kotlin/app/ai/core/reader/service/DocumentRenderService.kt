package app.ai.core.reader.service

import org.springframework.ai.document.Document
import java.io.InputStream

interface DocumentRenderService {

    fun render(
        inputStream : InputStream,
        originalFileName : String,
        ragId : Long,
    ) : List<Document>

}