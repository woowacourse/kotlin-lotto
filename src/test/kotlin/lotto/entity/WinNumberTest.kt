package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinNumberTest {
    @Test
    fun `당첨 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { WinNumber(listOf(1, 2, 3, 4, 5)) }
    }
}
