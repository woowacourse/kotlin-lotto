package lotto.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["0", "46", "a"])
    fun `로또 번호는 1~45 사이의 자연수를 갖지 않으면 오류를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> { LottoNumber(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "45", "20"])
    fun `로또 번호는 1~45 사이의 자연수를 갖는다`(input: String) {
        assertDoesNotThrow { LottoNumber(input) }
    }
}
