package model

import lotto.model.Margin
import lotto.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `돈이 0원 미만이면 예외를 던지는지`() {
        val amount = -1L

        val exception = assertThrows<IllegalArgumentException> { Money(amount) }

        assertThat(exception.message).isEqualTo("${amount}원은 안됩니다. 0원 이상이어야 합니다.")
    }

    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val actual = 0.357
        val winningPrize = Money(5_000)
        val money = Money(14_000)
        val expected = money.calculateMargin(winningPrize)

        assertThat(expected).isEqualTo(Margin(actual))
    }
}
