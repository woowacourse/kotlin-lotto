package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `입력받은 당첨번호와 보너스 번호가 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 5)
        }
    }
}