package lotto

import lotto.model.WinningPrizeCalculator
import lotto.model.WinningRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningPrizeCalculatorTest {
    @Test
    fun `수익률 계산`() {
        val result = (55000 / 10000).toDouble()
        assertThat(
            WinningPrizeCalculator.calculateProfitRate(
                10000,
                mapOf<WinningRank, Int>(WinningRank.FIFTH to 1, WinningRank.FOURTH to 1),
            ),
        ).isEqualTo(result)
    }
}
