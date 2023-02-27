package model

import io.kotest.matchers.throwable.haveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {

    @Test
    fun `내부로 들어오는 인자가, 1~45이외의 범위일 때, 예외를 발생한다`() {
        // given
        val testNumber = 46

        // then
        assertThrows<IllegalArgumentException> {
            LottoNumber(testNumber)
            haveMessage("[ERROR] 로또 번호가 1~45가 아닙니다")
        }
    }
}
