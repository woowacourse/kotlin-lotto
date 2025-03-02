package lotto

import lotto.domain.LottoNumber
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 10, 22, 44])
    fun `로또 번호는 1~45 숫자 사이에 해당한다`(value: Int) {
        assertDoesNotThrow { LottoNumber.of(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 49, 0, 46])
    fun `로또 번호는 1~45 숫자 사이에 없을 경우 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber.of(value) }
    }
}
