package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class YieldRateCalculatorTest {

    @Test
    fun `14000원으로 5000원을 번다면 수익률은 35%이다`() {
        assertThat(YieldRateCalculator.getYieldRate(PurchaseMoney(14000), 5000)).isEqualTo(0.35714285714285715)
    }
}
