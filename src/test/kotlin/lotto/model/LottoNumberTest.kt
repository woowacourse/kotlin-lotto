package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46, 100])
    fun `로또 번호가 허용 범위를 벗어나면 예외를 발생시킨다`(num: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(num)
        }
    }
}
