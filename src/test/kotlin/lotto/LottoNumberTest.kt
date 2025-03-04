package lotto

import lotto.model.LottoNumber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `1부터 45 사이의 숫자로 로또 번호를 생성한다`() {
        for (i in 1..45) {
            val lottoNumber = LottoNumber.from(i)
            assertEquals(i.toString(), lottoNumber.toString())
        }
    }

    @Test
    fun `1보다 작은 숫자로 로또 번호를 생성하면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                LottoNumber.from(0)
            }
        assertEquals("로또 번호는 1에서 45 범위 내에서 있어야 합니다.", exception.message)
    }

    @Test
    fun `45보다 큰 숫자로 로또 번호를 생성하면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                LottoNumber.from(46)
            }
        assertEquals("로또 번호는 1에서 45 범위 내에서 있어야 합니다.", exception.message)
    }
}
