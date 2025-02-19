package lotto

import lotto.model.WinningNumbers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `당첨번호가 중복되면 예외가 발생한다 `() {
        assertThrows<IllegalArgumentException> { WinningNumbers(listOf(1, 2, 3, 4, 6, 6)) }
    }

    @Test
    fun `당첨번호가 6개가 아니면 예외가 발생한다 `() {
        assertThrows<IllegalArgumentException> { WinningNumbers(listOf(1, 2, 3, 4, 5, 6, 7)) }
    }

    @Test
    fun `당첨 번호는 1부터 45 범위 내에 있어야 한다 `() {
        assertThrows<IllegalArgumentException> { WinningNumbers(listOf(1, 2, 3, 4, 5, 46)) }
    }
}
