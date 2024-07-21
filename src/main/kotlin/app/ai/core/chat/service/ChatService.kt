package app.ai.core.chat.service

interface ChatService {

    fun chat(prompt : String, query : String, ragId : Long) : String

}