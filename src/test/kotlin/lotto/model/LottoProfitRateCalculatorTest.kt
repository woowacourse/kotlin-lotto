package lotto.model

import lotto.entity.Money
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoProfitRateCalculatorTest {
    @Test
    fun `구입금액이 14000원이고 당첨금이 5000원이면 수익률은 0_35이다`() {
        // given
        val purchaseMoney = PurchaseMoney(14000)
        val winMoney = Money(5000)
        val calculator = LottoProfitRateCalculator()

        // when
        val profitRate = calculator.calculate(purchaseMoney, winMoney).roundDown()
        val except = ProfitRate(0.35f).roundDown()

        // then
        assertThat(profitRate).isEqualTo(except)
    }
}
