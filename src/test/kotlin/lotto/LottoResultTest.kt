package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `당첨 등수를 확인할 수 있다`() {
        val lotto = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val bonusNumber: String = "7"
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 8, 4, 5, 9)), bonusNumber)

        val rank = LottoResult(lotto, winningLotto).getRanks()

        assertThat(rank).containsExactly(Rank.FOURTH)
    }

    @Test
    fun `당첨 수익률을 계산할 수 있다`() {
        val lotto = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val bonusNumber: String = "7"
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 8, 4, 5, 9)), bonusNumber)

        val rank = LottoResult(lotto, winningLotto).calculateProfitRate()

        assertThat(rank).isEqualTo(50.0)
    }
}
