package lotto.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 23, 45])
    fun `로또 번호는 1부터 45 사이의 값을 가진다`(value: Int) {
        assertDoesNotThrow { LottoNumber(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun `로또 번호가 1부터 45 사이의 값이 아닐 경우 오류가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(value) }
    }
}
