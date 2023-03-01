package model

import model.LottoNumber.Companion.LOTTO_NUMBER_RANGE_ERROR_MESSAGE
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {

    @Test
    fun `로또 번호값에 '0'이 존재한다`() {
        // given
        val number = 0

        // when, then
        assertThrows<IllegalArgumentException>(LOTTO_NUMBER_RANGE_ERROR_MESSAGE) {
            LottoNumber(number)
        }
    }

    @Test
    fun `로또 번호값에 '50'이 존재한다`() {
        // given
        val number = 50

        // when, then
        assertThrows<IllegalArgumentException>(LOTTO_NUMBER_RANGE_ERROR_MESSAGE) {
            LottoNumber(number)
        }
    }
}
