package lotto

import lotto.domain.LottoNumber
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호는 1~45 숫자 사이에 없을 경우 예외가 발생한다`(num: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(num) }
    }
}
