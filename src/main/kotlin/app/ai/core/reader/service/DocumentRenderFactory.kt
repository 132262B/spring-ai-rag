package app.ai.core.reader.service

import app.ai.constant.ExtensionType

class DocumentRenderFactory {

    fun getInstance(
        type: ExtensionType,
    ): DocumentRenderService = when (type) {
        ExtensionType.PDF -> PdfDocumentRenderService()
        ExtensionType.TXT -> TextDocumentRenderService()
    }
}
