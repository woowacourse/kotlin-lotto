package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarginCalculatorTest {
    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val marginCalculator = MarginCalculator()
        val winningPrize = Money(5000)
        val purchaseAmount = Money(14000)
        val actual = marginCalculator.calculate(winningPrize, purchaseAmount)
        assertThat(actual).isEqualTo(0.357)
    }
}
