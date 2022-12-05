import java.io.File

interface FileContentValidator {
    fun getValidLines(file: File): MutableList<String>
}

class FileContentValidatorImpl: FileContentValidator {
    override fun getValidLines(file: File): MutableList<String> {
        val result = mutableListOf<String>()
        val contentRegex = contentRegex

        file.forEachLine { line ->
            if (contentRegex.matches(line)) {
                result.add(line)
            }
        }

        return result
    }

    companion object {
        private val contentRegex = "\\w\\s+\\d-\\d:\\s+[\\w\\d]+".toRegex()
    }
}