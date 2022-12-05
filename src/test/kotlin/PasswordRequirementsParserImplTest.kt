import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
internal class PasswordRequirementsParserImplTest(
    private val lines: List<String>,
    private val passwordRequirements: List<PasswordRequirements>
) {
    private val passwordRequirementsParser: PasswordRequirementsParser = PasswordRequirementsParserImpl()

    @Test
    fun test() {
        assertEquals(passwordRequirements, passwordRequirementsParser.parse(lines))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "input strings: {0}, expected objects: {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    listOf(
                        "a 1-5: abcdj",
                        "z 2-4: asfalseiruqwo",
                        "b 3-6: bhhkkbbjjjb"
                    ),
                    listOf(
                        PasswordRequirements("abcdj", 'a', 1..5),
                        PasswordRequirements("asfalseiruqwo", 'z', 2..4),
                        PasswordRequirements("bhhkkbbjjjb", 'b', 3..6)
                    )
                )
            )
        }
    }
}