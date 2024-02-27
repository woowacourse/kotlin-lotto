package lotto.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 10, 25, 45])
    fun `로또 번호가 로또 범위에 포함되는 경우 예외가 발생하지 않는다`(number: Int) =
        assertDoesNotThrow {
            LottoNumber.from(number)
        }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 50, 60])
    fun `로또 번호가 로또 범위에 벗어나는 경우 예외가 발생한다`(number: Int) =
        assertThrows<IllegalArgumentException> {
            LottoNumber.from(number)
        }
}
