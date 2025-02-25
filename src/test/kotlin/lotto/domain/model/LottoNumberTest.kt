package lotto.domain.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 25, 45])
    fun `로또 번호는 1에서 45 범위여야 한다`(number: Int) {
        assertDoesNotThrow { LottoNumber(number) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46, -1])
    fun `로또 번호가 1에서 45 범위를 벗어나면 예외를 발생시킨다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }
}
