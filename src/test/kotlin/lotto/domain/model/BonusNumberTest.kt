package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BonusNumberTest {
    @Test
    fun `보너스 번호가 당첨 번호랑 중복되면 예외가 발생한다`() {
        val winningNumbers = WinningNumbers(List(6) { LottoNumber(it + 1) })

        assertThrows<IllegalArgumentException> {
            BonusNumber(winningNumbers, LottoNumber(1))
        }
    }
}
