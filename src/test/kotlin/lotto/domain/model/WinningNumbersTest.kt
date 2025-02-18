package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `당첨 번호는 6개의 숫자를 가진다`() {
        val numbers = List(7) { LottoNumber(it + 1) }
        assertThrows<IllegalArgumentException> {
            WinningNumbers(numbers)
        }
    }
}
