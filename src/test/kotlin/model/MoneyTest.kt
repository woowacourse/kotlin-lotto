package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {
    @Test
    fun `돈 객체 간의 덧셈 연산을 수행한다`() {
        assertThat(MONEY_1500 + MONEY_1000).isEqualTo(Money(BigDecimal(2_500)))
    }

    @Test
    fun `돈 객체 간의 뺄셈 연산을 수행한다`() {
        assertThat(MONEY_1500 - MONEY_1000).isEqualTo(Money(BigDecimal(500)))
    }

    @Test
    fun `돈 객체 간의 곱셈 연산을 수행한다`() {
        val count = 4
        assertThat(MONEY_1500 * count).isEqualTo(Money(BigDecimal(6_000)))
    }

    @Test
    fun `돈 객체 간의 나눗셈 연산을 수행한다`() {
        assertThat(MONEY_1500 / MONEY_1000).isEqualTo(1.50)
    }

    @Test
    fun `돈 객체 간의 나머지 연산을 수행한다`() {
        assertThat(MONEY_1500 % MONEY_1000).isEqualTo(Money(BigDecimal(500)))
    }

    @Test
    fun `돈 객체 간의 대소를 비교한다`() {
        assertTrue(MONEY_1500 > MONEY_1000)
    }

    companion object {
        private val MONEY_1500 = Money(BigDecimal(1_500))
        private val MONEY_1000 = Money(BigDecimal(1_000))
    }
}
