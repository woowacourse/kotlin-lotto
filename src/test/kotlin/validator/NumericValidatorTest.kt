package validator

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NumericValidatorTest {
    @Test
    fun `빈 값이 입력되면 예외가 발생한다`() {
        Assertions
            .assertThatThrownBy {
                NumericValidator("   ")
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 빈 값을 입력하셨습니다.")
    }

    @ValueSource(strings = ["가나다, abc, ###, @we2as"])
    @ParameterizedTest
    fun `숫자가 아닌 값이 입력되면 예외가 발생한다`(value: String) {
        Assertions
            .assertThatThrownBy {
                NumericValidator(value)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 숫자를 입력해야 합니다.")
    }
}
