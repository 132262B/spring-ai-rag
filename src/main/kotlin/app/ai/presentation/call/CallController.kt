package app.ai.presentation.call

import app.ai.core.chat.usecase.GetChat
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/call")
class CallController(
    private val getChat: GetChat
) {

    @GetMapping("/chat")
    fun chat(
        @RequestParam prompt: String,
        @RequestParam query: String,
        @RequestParam ragId: Long
    ): String = getChat.execute(
        GetChat.Input(
            prompt = prompt,
            query = query,
            ragId = ragId
        )
    )


}