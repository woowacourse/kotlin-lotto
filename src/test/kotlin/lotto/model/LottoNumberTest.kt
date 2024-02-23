package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 46, 100])
    fun `로또 번호는 1부터 45사이의 자연수로 구성이 된다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.of(number)
        }
    }
}
