package lotto

import lotto.model.LottoNumber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46, -100])
    fun `로또 번호는 1~45 사이의 숫자가 아닐 경우 예외를 발생한다`(number: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(number) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 45, 10])
    fun `로또 번호는 1~45 사이의 숫자 일 경우 예외를 발생하지 않는다`(number: Int) {
        Assertions.assertDoesNotThrow {
            LottoNumber(number)
        }
    }
}
