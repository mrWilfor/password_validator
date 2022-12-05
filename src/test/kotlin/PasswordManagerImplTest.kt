import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File

@RunWith(value = Parameterized::class)
internal class PasswordManagerImplTest(
    private val inputFile: File,
    private val numberOfValidPasswords: Int
) {
    private val passwordManager: PasswordManager = PasswordManagerImpl(
        FileContentValidatorImpl(),
        PasswordRequirementsParserImpl(),
        PasswordValidatorImpl()
    )

    @Test
    fun test() {
        assertEquals(numberOfValidPasswords, passwordManager.countValidPasswords(inputFile))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "input file: {0}, expected: {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    File(this.javaClass.getResource("/test.txt")!!.toURI()),
                    2
                )
            )
        }
    }
}