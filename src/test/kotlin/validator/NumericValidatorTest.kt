package validator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NumericValidatorTest {
    @Test
    fun `빈 값이 입력되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 빈 값을 입력하셨습니다.",
        ) {
            NumericValidator("   ")
        }
    }

    @ValueSource(strings = ["가나다, abc, ###, @we2as"])
    @ParameterizedTest
    fun `숫자가 아닌 값이 입력되면 예외가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 숫자를 입력해야 합니다.",
        ) {
            NumericValidator(value)
        }
    }
}
