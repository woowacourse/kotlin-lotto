package lotto.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46, 100])
    fun `로또 번호가 허용 범위를 벗어나면 예외를 발생시킨다`(num: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.of(num)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 30, 45])
    fun `로또 번호가 허용 범위 안에 있으면 예외가 발생하지 않는다`(num: Int) {
        assertDoesNotThrow {
            LottoNumber.of(num)
        }
    }
}
