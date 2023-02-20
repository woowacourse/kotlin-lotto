package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 숫자는 1과 45 사이다`() {
        assertThrows<IllegalArgumentException> { LottoNumber.from(47) }
    }
}
