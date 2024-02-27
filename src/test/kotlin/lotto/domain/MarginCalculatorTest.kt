package lotto.domain

import lotto.domain.model.Margin
import lotto.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarginCalculatorTest {

    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val winningPrize = Money(5000)
        val purchaseAmount = Money(14000)
        val actual = MarginCalculator.calculateMarginRate(winningPrize, purchaseAmount)
        assertThat(actual).isEqualTo(Margin(0.35714285714285715))
    }
}
