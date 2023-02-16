package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {

    @Test
    fun `구입금액은 천원이상이여야 한다`() {
        // given
        val money = 800

        // then
        assertThrows<IllegalArgumentException> {
            Money(money)
        }
    }

    @Test
    fun `구입급액은 천원단위로 나누어떨어져야 한다`() {
        // given
        val money = 1500

        // then
        assertThrows<IllegalArgumentException> {
            Money(money)
        }
    }
}
