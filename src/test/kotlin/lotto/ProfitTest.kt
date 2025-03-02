package lotto

import lotto.model.Profit
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.floor

class ProfitTest {
    @Test
    fun `당첨 금액이 0원일 때 수익률은 0이어야 한다`() {
        // given
        val purchase = 5000.0
        val winningStatistics = mapOf<Rank, Int>()

        // when
        val result = Profit.calculateProfit(purchase, winningStatistics)

        // then
        assertThat(result).isEqualTo("0.0")
    }

    @Test
    fun `총 수익률을 계산한다`() {
        // given
        val purchase = 5000.0
        val winningStatistics =
            mapOf(
                Rank.FIRST to 1,
            )

        // when
        val result = Profit.calculateProfit(purchase, winningStatistics)

        // then
        val expectedProfit = floor((Rank.FIRST.winningMoney.toDouble() / purchase) * 100) / 100
        assertThat(result).isEqualTo(expectedProfit.toString())
    }
}
