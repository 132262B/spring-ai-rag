package app.ai.infrastructure.openai

interface OpenAiRepository {
    fun chat(prompt : String, query : String, ragId : Long) : String

}