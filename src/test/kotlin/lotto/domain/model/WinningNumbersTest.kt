package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `당첨 번호는 6개의 숫자를 가진다`() {
        val lotto = (1..7).toList()
        assertThrows<IllegalArgumentException> {
            WinningNumbers(lotto, 8)
        }
    }
}
