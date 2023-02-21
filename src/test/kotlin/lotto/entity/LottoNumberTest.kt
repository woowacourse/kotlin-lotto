package lotto.entity

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ValueSource(ints = [0, 46, 100])
    @ParameterizedTest
    fun `번호가 1에서 45 사이 숫자가 아니면 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(value) }
    }
}
