package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 번호가 1과 45 사이의 숫자가 아닌 경우 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoNumber(47) }
    }
}
