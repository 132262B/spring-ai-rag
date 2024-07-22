package app.ai.core.dataset.usecase

import app.ai.constant.ExtensionType
import app.ai.core.UseCase
import app.ai.core.dataset.service.DataSetService
import app.ai.core.reader.service.DocumentRenderFactory
import app.ai.util.FileUtils
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class CreateDataSet(
    private val dataSetService: DataSetService,
) : UseCase<CreateDataSet.Input, Unit> {

    data class Input(
        val ragId: Long,
        val file: MultipartFile,
    )

    override fun execute(input: Input) = DocumentRenderFactory()
        .getInstance(type = ExtensionType.from(FileUtils.getFileExtension(input.file.originalFilename)))
        .render(
            inputStream = input.file.inputStream,
            originalFileName = input.file.originalFilename ?: "",
            ragId = input.ragId
        )
        .let(dataSetService::create)

}
