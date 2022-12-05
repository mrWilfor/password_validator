data class PasswordRequirements(
    val password: String,
    val symbol: Char,
    val repeatRange: IntRange
)
