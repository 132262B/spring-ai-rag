package app.ai.core

fun interface UseCase<I, O> {

    fun execute(input: I): O

}