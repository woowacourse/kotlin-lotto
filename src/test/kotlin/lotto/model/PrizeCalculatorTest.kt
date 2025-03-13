package lotto.model

import lotto.domain.model.Rank
import lotto.domain.service.RankCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrizeCalculatorTest {
    @Test
    fun `순위 리스트에 따라 총 당첨금을 계산한다`() {
        val rankCalculator = RankCalculator()
        val winningList = mapOf(Rank.FIRST to 1, Rank.SECOND to 1)
        val totalPrize = Rank.FIRST.winningMoney + Rank.SECOND.winningMoney
        assertThat(rankCalculator.earningMoney(winningList)).isEqualTo(totalPrize)
    }

    @Test
    fun `당첨금과 투자 금액으로 수익률을 계산한다`() {
        val rankCalculator = RankCalculator()
        val winningList = listOf(Rank.FIRST, Rank.SECOND)
        val totalPrize = Rank.FIRST.winningMoney + Rank.SECOND.winningMoney
        assertThat(rankCalculator.calculateEarningRate(2000, totalPrize))
            .isEqualTo(totalPrize / 2000.0)
    }
}
