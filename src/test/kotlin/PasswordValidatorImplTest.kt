import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
internal class PasswordValidatorImplTest(
    private val passwordRequirements: PasswordRequirements,
    private val expected: Boolean
) {
    private val passwordValidator: PasswordValidator = PasswordValidatorImpl()

    @Test
    fun test() {
        assertEquals(expected, passwordValidator.validate(passwordRequirements))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "requirements: {0}, expected: {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    PasswordRequirements("abcdj", 'a', 1..5),
                    true
                ),
                arrayOf(
                    PasswordRequirements("asfalseiruqwo", 'z', 2..4),
                    false
                ),
                arrayOf(
                    PasswordRequirements("bhhkkbbjjjb", 'b', 3..6),
                    true
                )
            )
        }
    }
}