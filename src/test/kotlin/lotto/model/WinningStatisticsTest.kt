package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningStatisticsTest {
    @Test
    fun `로또 수익률 계산 확인`() {
        val purchaseAmount = 14_000
        val result = mapOf(Rank.FIFTH to 1)
        val winningStatistics = WinningStatistics(result)

        val actual = winningStatistics.calculateRateOfReturn(purchaseAmount)
        val expected = 0.35714285714285715

        assertThat(actual).isEqualTo(expected)
    }
}
