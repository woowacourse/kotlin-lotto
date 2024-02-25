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

    private companion object {
        const val MINUS_MONEY_AMOUNT = -1
        const val GENERAL_MONEY_AMOUNT = 1_000
    }
}
