package lotto.domain.value

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 30, 40, 45])
    fun `로또 번호는 1~45 사이의 숫자이다`(number: Int) {
        assertDoesNotThrow {
            LottoNumber(number)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호가 1~45 사이의 숫자가 아니면 예외가 발생한다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }
}
