import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import java.io.File
import kotlin.test.assertEquals

@RunWith(value = Parameterized::class)
internal class FileContentValidatorImplTest(
    private val file: File,
    private val validStrings: List<String>
) {
    private val fileContentValidator: FileContentValidator = FileContentValidatorImpl()

    @Test
    fun test() {
        assertEquals(validStrings, fileContentValidator.getValidLines(file))
    }

    companion object {
        @JvmStatic
        @Parameters(name = "input file: {0}, expected list: {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    File(this.javaClass.getResource("/test.txt")!!.toURI()),
                    listOf(
                        "a 1-5: abcdj",
                        "z 2-4: asfalseiruqwo",
                        "b 3-6: bhhkkbbjjjb"
                    )
                )
            )
        }
    }
}