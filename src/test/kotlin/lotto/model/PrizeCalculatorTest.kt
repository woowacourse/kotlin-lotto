package lotto.model

import lotto.domain.model.Rank
import lotto.domain.service.RankCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrizeCalculatorTest {
    @Test
    fun `순위 리스트에 따라 당첨금을 반환한다`() {
        val rankCalculator = RankCalculator()
        val winningList = listOf(Rank.FIRST, Rank.SECOND)
        assertThat(rankCalculator.earningMoney(winningList)).isEqualTo(Rank.FIRST.winningMoney + Rank.SECOND.winningMoney)
    }

    @Test
    fun `당첨금과 입력 금액에 따라 수익률을 반환한다`() {
        val rankCalculator = RankCalculator()
        val winningList = listOf(Rank.FIRST, Rank.SECOND)
        assertThat(
            rankCalculator.calculateEarningRate(
                2000,
                rankCalculator.earningMoney(winningList),
            ),
        ).isEqualTo((Rank.FIRST.winningMoney + Rank.SECOND.winningMoney) / 2000.0)
    }
}
