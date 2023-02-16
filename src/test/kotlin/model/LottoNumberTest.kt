package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {

    @Test
    fun `당첨번호와 보너스번호는 1~45이다`() {
        // given
        val lottoNumber = 46

        // then
        assertThrows<IllegalArgumentException> {
            LottoNumber(lottoNumber)
        }
    }
}
