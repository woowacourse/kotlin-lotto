import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NumericValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = ["abc", "*", "우기"])
    fun `입력이 형태의 스트링이 아닌 경우`(input: String) {
        assertThrows<IllegalArgumentException> {
            NumericValidator().validate(input)
        }
    }
}
