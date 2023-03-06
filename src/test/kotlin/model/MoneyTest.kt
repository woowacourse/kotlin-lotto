package model

import model.Money.Companion.ERROR_CHECK_PRICE_UNIT
import model.Money.Companion.ERROR_UNDER_MINIMUM_PRICE
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `구입 금액은 1000원 이하인, 500원이다`() {
        // given
        val money = 500

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_UNDER_MINIMUM_PRICE) {
            Money(money)
        }
    }

    @Test
    fun `구입 금액은 1000원 단위가 아닌, 1500원이다`() {
        // given
        val money = 1500

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_CHECK_PRICE_UNIT) {
            Money(money)
        }
    }
}
