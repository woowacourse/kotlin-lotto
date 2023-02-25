package lotto.entity

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinStatisticsTest {
    @Test
    fun `5등이 2개이면 상금은 10000원이다`() {
        // given
        val winStatistics = WinStatistics(mapOf(Rank.FIFTH to 2))

        // when
        val winMoney = winStatistics.calculateWinMoney()

        // then
        assertThat(winMoney.value).isEqualTo(10000)
    }

    @Test
    fun `구입금액이 14000원이고 당첨금이 5000원이면 수익률은 0_35이다`() {
        // given
        val purchaseMoney = PurchaseMoney(14000)
        val winStatistics = WinStatistics(mapOf(Rank.FIFTH to 1))

        // when
        val profitRate = winStatistics.calculateProfitRate(purchaseMoney).roundDown()
        val except = ProfitRate(0.35f).roundDown()

        // then
        assertThat(profitRate).isEqualTo(except)
    }
}
