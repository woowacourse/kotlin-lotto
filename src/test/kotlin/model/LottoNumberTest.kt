package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {

    @Test
    fun `로또 번호가 46이 생성되었을 때, 익셉션을 던진다`() {
        // given
        val testNumber = 46

        // then
        assertThrows<IllegalArgumentException>("[ERROR] 로또 번호가 1~45가 아닙니다") {
            LottoNumber(testNumber)
        }
    }

    @Test
    fun `로또 번호가 0이 생성되었을 때, 익셉션을 던진다`() {
        // given
        val testNumber = 0

        // then
        assertThrows<IllegalArgumentException>("[ERROR] 로또 번호가 1~45가 아닙니다") {
            LottoNumber(testNumber)
        }
    }
}
