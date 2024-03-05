package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(
        ints = [-1, 0, 46],
    )
    fun `로또 번호는 1 ~ 45 이어야 한다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.of(number)
        }
    }
}
