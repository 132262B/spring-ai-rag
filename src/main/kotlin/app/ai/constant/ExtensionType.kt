package app.ai.constant

enum class ExtensionType {

    PDF,
    TXT,
    ;

    companion object {

        fun from(type: String): ExtensionType = ExtensionType.valueOf(type.uppercase())

        fun isType(type: String): Boolean = ExtensionType.entries.any { it.name == type.uppercase() }

    }

}