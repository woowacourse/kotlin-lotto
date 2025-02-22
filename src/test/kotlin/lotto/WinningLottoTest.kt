package lotto

import lotto.domain.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `보너스 볼은 당첨 번호와 중복되지 않는다`() {
        val winningNumbers = lottoOf(1, 2, 3, 4, 5, 6)
        val bonusNum: Int = 6

        assertThrows<IllegalArgumentException> { WinningLotto(winningNumbers, bonusNum) }
    }

    @Test
    fun `당첨 번호는 정수 형태로 입력되어야 한다`() {
        val winningNumbers = lottoOf(1, 2, 3, 4, 5, 6)
        val bonusNum: String = "lotto"

        assertThrows<IllegalArgumentException> { WinningLotto(winningNumbers, bonusNum.toInt()) }
    }
}
