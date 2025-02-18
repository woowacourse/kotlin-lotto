package lotto

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 46, 47])
    fun `로또 번호가 1~45가 아닌 경우 예외가 발생한다`(numbers: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(numbers)
        }
    }
}
