interface PasswordValidator {
    fun validate(passwordRequirements: PasswordRequirements): Boolean
}

class PasswordValidatorImpl: PasswordValidator {

    override fun validate(passwordRequirements: PasswordRequirements): Boolean {
        with(passwordRequirements) {
            return password.count { it == symbol } in repeatRange
        }
    }
}