import java.io.File

interface PasswordManager {
    fun countValidPasswords(sourceFile: File): Int
}

class PasswordManagerImpl(
    private val fileContentValidator: FileContentValidator,
    private val passwordRequirementsParser: PasswordRequirementsParser,
    private val passwordValidator: PasswordValidator
): PasswordManager {
    override fun countValidPasswords(sourceFile: File): Int {
        val validLines = fileContentValidator.getValidLines(sourceFile)
        val passwordRequirements = passwordRequirementsParser.parse(validLines)

        return passwordRequirements.count(passwordValidator::validate)
    }
}