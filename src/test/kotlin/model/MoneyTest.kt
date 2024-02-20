package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {
    @Test
    fun `돈 객체 간의 뺄셈 연산을 수행한다`() {
        val money1 = Money(BigDecimal(1_500))
        val money2 = Money(BigDecimal(1_000))
        assertThat(money1 - money2).isEqualTo(Money(BigDecimal(500)))
    }

    @Test
    fun `돈 객체 간의 덧셈 연산을 수행한다`() {
        val money1 = Money(BigDecimal(1_500))
        val money2 = Money(BigDecimal(1_000))
        assertThat(money1 + money2).isEqualTo(Money(BigDecimal(2_500)))
    }

    @Test
    fun `돈 객체 간의 곱셈 연산을 수행한다`() {
        val money1 = Money(BigDecimal(1_500))
        val count = 4
        assertThat(money1 * count).isEqualTo(Money(BigDecimal(6_000)))
    }

    @Test
    fun `돈 객체 간의 나눗셈 연산을 수행한다`() {
        val money1 = Money(BigDecimal(1_500))
        val money2 = Money(BigDecimal(1_000))
        assertThat(money1 / money2).isEqualTo(Money(BigDecimal("1.50")))
    }

}
