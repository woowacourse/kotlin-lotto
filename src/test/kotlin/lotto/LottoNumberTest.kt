package lotto

import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46, -100])
    fun `로또 번호는 1~45 사이의 숫자가 아닐 경우 예외를 발생한다`(number: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { LottoNumber(number) }
            .withMessage("${number}는 범위를 벗어났습니다. 로또 번호는 1 ~ 45 사이의 숫자입니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 45, 10])
    fun `로또 번호는 1~45 사이의 숫자 일 경우 예외를 발생하지 않는다`(number: Int) {
        Assertions.assertDoesNotThrow {
            LottoNumber(number)
        }
    }
}
