package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningNumbersTest {
    @Test
    fun `당첨번호와 로또 번호를 비교해, 몇개 일치하는 지 확인`() {
        val lottoNumbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumbers: List<Int> = listOf(1, 2, 7, 8, 9, 10)
        val bonusNumber = 12
        assertThat(winningNumbers.intersect(lottoNumbers).size).isEqualTo(2)
        assertThat(bonusNumber in lottoNumbers).isFalse()
    }

    @Test
    fun `수익률 계산`() {
        val winningMoney = Rank.THIRD.winningMoney
        val winningCount = 10
        val purchaseAmount = 20_000

        assertThat(String.format("%.2f", winningMoney * winningCount / purchaseAmount.toDouble())).isEqualTo("750.00")
    }
}
