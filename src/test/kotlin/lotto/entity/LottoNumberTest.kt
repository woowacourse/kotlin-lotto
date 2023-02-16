package lotto.entity

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [0, 46, 52])
    @ParameterizedTest
    fun `로또 번호가 1에서 45 사이의 범위에 있지 않으면 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(value) }
    }
}
