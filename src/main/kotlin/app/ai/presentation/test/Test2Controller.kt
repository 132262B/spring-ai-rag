package app.ai.presentation.test

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.print.Doc


@RestController
class Test2Controller(
//    private val openAiChatModel: OpenAiChatModel
    @Value("\${key.open-ai}") private val apiKey: String,
    private val vectorStore: VectorStore,
) {

//    @PostMapping("/test3")
//    fun pdfReader(@RequestPart("file") file : MultipartFile ) {
//
//        val resource = InputStreamResource(file.inputStream, file.originalFilename)
//
//        val pdfReader: PagePdfDocumentReader = PagePdfDocumentReader(
//            resource,
//            PdfDocumentReaderConfig.builder()
//                .withPageExtractedTextFormatter(
//                    ExtractedTextFormatter.Builder().withNumberOfBottomTextLinesToDelete(3)
//                        .withNumberOfTopPagesToSkipBeforeDelete(1)
//                        .build()
//                )
//                .withPagesPerDocument(1)
//                .build()
//        )
//        println(pdfReader.get())
////        vectorStore.add(pdfReader.get())
//    }


    @PostMapping("/test4")
    fun pdfReader2(@RequestPart("file") file: MultipartFile) {
        val modifiedText = StringBuilder()

        file.inputStream.use { inputStream ->
            val document = PDDocument.load(inputStream)
            val pdfTextStripper = PDFTextStripper()


            val lines = pdfTextStripper.getText(document).lines()
            for (line in lines) {
                modifiedText.append(replaceUnicodeWithSpace(line))
                modifiedText.append("\n") // 띄어쓰기 추가
            }
        }

        println(modifiedText)
    }


    @PostMapping("/test5")
    fun pdfReader3(@RequestPart("file") file: MultipartFile) {

        val list: MutableList<Document> = mutableListOf()

        file.inputStream.use { inputStream ->
            val document = PDDocument.load(inputStream)
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
                            "file_name" to file.originalFilename,
                        )
                    )
                )

//                modifiedText.append("\n\n") // 페이지 구분을 위해 추가
            }

            document.close()
        }

        println(list)
        vectorStore.add(list)
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