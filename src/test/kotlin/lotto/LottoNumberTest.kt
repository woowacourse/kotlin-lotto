package lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46, -100])
    fun `로또 번호는 1~45 사이의 숫자가 아닐 경우 예외를 발생한다`(number: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { LottoNumber(number) }
            .withMessage("로또 번호는 1 ~ 45 사이의 숫자입니다.")
    }
}
