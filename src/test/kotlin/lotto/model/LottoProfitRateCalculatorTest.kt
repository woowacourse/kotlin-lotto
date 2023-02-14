package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoProfitRateCalculatorTest {
    @Test
    fun `구입금액이 14000원이고 당첨금이 5000원이면 수익률은 0_35이다`() {
        val calculator = LottoProfitRateCalculator(14000, listOf(Rank.FIFTH))
        assertThat((calculator.calculate().value * 100).toInt() / 100f).isEqualTo(0.35f)
    }
}
