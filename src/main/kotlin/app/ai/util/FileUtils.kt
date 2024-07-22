package app.ai.util

object FileUtils {
    fun getFileExtension(fileName: String?): String = fileName
        ?.substringAfterLast(DOT, EMPTY)
        ?: throw IllegalArgumentException("Invalid file name")



    private const val DOT : String = "."
    private const val EMPTY : String = ""

}