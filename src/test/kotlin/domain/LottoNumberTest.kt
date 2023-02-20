package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoNumberTest {
    @Test
    fun `로또는 1에서 45 사이의 숫자다`() {
        assertDoesNotThrow { LottoNumber.from(1) }
        assertDoesNotThrow { LottoNumber.from(45) }
        assertDoesNotThrow { LottoNumber.from(10) }
    }

    @Test
    fun `로또는 1에서 45 사이의 숫자가 아니면 에러발생한다`() {
        assertThrows<IllegalArgumentException> { LottoNumber.from(0) }
        assertThrows<IllegalArgumentException> { LottoNumber.from(46) }
    }

    @Test
    fun `LottoNumber의 동일성 검사`() {
        assertThat(LottoNumber.from(17)).isEqualTo(LottoNumber.from(17))
    }
}
