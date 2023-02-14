package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoNumbersTest {
    @Test
    fun `숫자가 6개인 로또 번호를 뽑는다`() {
        assertDoesNotThrow {
            LottoNumbers(listOf(1, 24, 34, 35, 36, 38))
        }
    }

    @Test
    fun `숫자가 6개가 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoNumbers(listOf(1, 24, 34, 35, 37, 45, 22))
        }
    }
}
