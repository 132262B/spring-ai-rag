package app.ai.presentation.dataset

import app.ai.core.dataset.usecase.CreateDataSet
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/data-set")
class StoreController(
    private val createDataSet: CreateDataSet
) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun create(
        @RequestPart("file") file: MultipartFile,
        ragId: Long,
    ) = createDataSet.execute(
        CreateDataSet.Input(
            ragId = ragId,
            file = file
        )
    )

}