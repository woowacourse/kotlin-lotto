package lotto

import lotto.domain.WinningCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningCalculatorTest {
    @Test
    fun `최종 통계를 통해 당첨금을 계산한다`() {
        val statistics = listOf(1, 1, 0, 1, 1, 1)
        val money = 2_000_000_000 + 30_000_000 + 50_000 + 5_000 + 0

        assertThat(
            WinningCalculator().getWinningMoney(statistics)
        ).isEqualTo(money)
    }

    @Test
    fun `수익률을 계산한다`() {
        val purchase = 14000
        val winning = 5000

        assertThat(
            WinningCalculator().getEarningRate(purchase, winning)
        ).isEqualTo(0.36)
    }
}
