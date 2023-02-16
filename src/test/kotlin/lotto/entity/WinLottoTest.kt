package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinLottoTest {
    @Test
    fun `보너스 번호와 당첨번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
            val bonus = LottoNumber(1)
            WinLotto(winNumber, bonus)
        }
    }
}
