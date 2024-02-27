package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `Money는 음수일 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Money(MINUS_MONEY_AMOUNT)
        }
    }

    @Test
    fun `Money를 Money로 나누면 정수 몫이 나온다`() {
        val count = Money(GENERAL_MONEY_AMOUNT) / Money(GENERAL_MONEY_AMOUNT)
        assertThat(count).isEqualTo(1)
    }

    @Test
    fun `Money 의 amount가 0일 때, Money로 나누면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            Money(GENERAL_MONEY_AMOUNT) / Money(0)
        }
    }

    @Test
    fun `Moneny 를 n 배로 곱하면, amount n배 인 Money가 된다`() {
        // given
        val times = 3
        val expectedMoney = Money(3_000)
        // when
        val actualMoney = Money(1_000) * times
        // then
        assertThat(actualMoney).isEqualTo(expectedMoney)
    }

    @Test
    fun `amount 값이 크면 더 큰 Money 이다`() {
        // given
        val smallMoney = Money(2_000)
        val bigMoney = Money(2_001)
        // when
        val actual = bigMoney > smallMoney
        // then
        assertThat(actual).isTrue()
    }

    private companion object {
        const val MINUS_MONEY_AMOUNT = -1
        const val GENERAL_MONEY_AMOUNT = 1_000
    }
}
