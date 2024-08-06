package app.ai.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController2 {

    @GetMapping("/aaa")
    fun aaa() : String {
        return "Hello World!"
    }

}