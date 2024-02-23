package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["-2", "0", "46", "a"])
    fun `로또 번호는 1~45 사이의 정수여야 한다`(input: String) {
        assertThrows<IllegalArgumentException> { LottoNumber(input) }
    }
}
