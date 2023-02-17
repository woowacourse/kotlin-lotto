package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoNumberTest {
    @Test
    fun `로또는 1에서 45 사이의 숫자다`() {
        assertDoesNotThrow { LottoNumber(1) }
        assertDoesNotThrow { LottoNumber(45) }
        assertDoesNotThrow { LottoNumber(10) }
    }

    @Test
    fun `로또는 1에서 45 사이의 숫자가 아니면 에러발생한다`() {
        assertThrows<IllegalArgumentException> { LottoNumber(0) }
        assertThrows<IllegalArgumentException> { LottoNumber(46) }
    }
}
