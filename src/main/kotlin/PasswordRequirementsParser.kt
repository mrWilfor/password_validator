interface PasswordRequirementsParser {
    fun parse(lines: List<String>): List<PasswordRequirements>
}

class PasswordRequirementsParserImpl : PasswordRequirementsParser {
    override fun parse(lines: List<String>): List<PasswordRequirements> {
        val result = mutableListOf<PasswordRequirements>()

        lines
            .map(String::trim)
            .forEach { line ->
                val password = parsePassword(line)
                val symbol = parseSymbol(line)
                val range = parseRange(line)

                result.add(PasswordRequirements(password, symbol, range))
            }

        return result
    }

    private fun parseSymbol(line: String): Char {
        val index = line.indexOf(' ') - 1

        return line[index]
    }

    private fun parseRange(line: String): IntRange {
        val dashIndex = line.indexOf('-')
        val firstIndexOfBottomBound = line.lastIndexOf(' ', dashIndex) + 1
        val firstIndexOfTopBound = dashIndex + 1
        val colonIndex = line.indexOf(':', dashIndex)
        val bottomBound = line.substring(firstIndexOfBottomBound, dashIndex).toInt()
        val topBound = line.substring(firstIndexOfTopBound, colonIndex).toInt()

        return bottomBound..topBound
    }

    private fun parsePassword(line: String): String {
        val firstIndexOfPassword = line.lastIndexOf(' ') + 1

        return line.substring(firstIndexOfPassword)
    }
}