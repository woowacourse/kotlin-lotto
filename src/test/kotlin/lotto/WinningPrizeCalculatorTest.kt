package lotto

import lotto.model.WinningPrizeCalculator
import lotto.model.WinningRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningPrizeCalculatorTest {
    @Test
    fun `총 상금 계산`() {
        assertThat(
            WinningPrizeCalculator.calculateProfitAmount(
                mapOf<WinningRank, Int>(WinningRank.FIFTH to 1, WinningRank.FOURTH to 1),
            ),
        ).isEqualTo(55000)
    }

    @Test
    fun `수익률 계산`() {
        val result = (50000 / 10000).toDouble()
        assertThat(WinningPrizeCalculator.calculateProfitRate(10000, 50000)).isEqualTo(result)
    }
}
