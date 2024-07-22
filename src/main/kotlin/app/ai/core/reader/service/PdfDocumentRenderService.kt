package app.ai.core.reader.service

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.ai.document.Document
import java.io.InputStream

class PdfDocumentRenderService : DocumentRenderService {

    override fun render(
        inputStream: InputStream,
        originalFileName: String,
        ragId: Long,
    ): List<Document> {
        val list: MutableList<Document> = mutableListOf()

        inputStream.use {
            val document = PDDocument.load(it)
            document.use {
                val pdfTextStripper = PDFTextStripper()

                val numberOfPages = document.numberOfPages

                val modifiedText = StringBuilder()
                for (page in 1..numberOfPages) {
                    pdfTextStripper.startPage = page
                    pdfTextStripper.endPage = page

                    val pageText = pdfTextStripper.getText(document)
                    val lines = pageText.lines()

                    for (line in lines) {
                        modifiedText.append(replaceUnicodeWithSpace(line))
                        modifiedText.append("\n") // 띄어쓰기 추가
                    }

                    list.add(
                        Document(
                            modifiedText.toString(), mapOf(
                                "page_number" to page,
                                "file_name" to originalFileName,
                                "rag_id" to ragId,
                            )
                        )
                    )
                }

                document.close()

            }
        }
        return list
    }

    private fun replaceUnicodeWithSpace(input: String): String {
        val output = StringBuilder()
        for (c in input) {
            if ((c in '\u0000'..'\u0020')) {
                output.append(' ')
            } else {
                output.append(c)
            }
        }
        return output.toString()
    }
}