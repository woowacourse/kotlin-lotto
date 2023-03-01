package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `구입 금액은 1000원 이하인, 500원이다`() {
        // given
        val money = 500

        // when
        val actual = Money(money).requireOverMinimumPrice()

        // then
        assertThat(actual).isEqualTo(null)
    }

    @Test
    fun `구입 금액은 1000원 단위가 아닌, 1500원이다`() {
        // given
        val money = 1500

        // when
        val actual = Money(money).requireCheckPriceUnit()

        // then
        assertThat(actual).isEqualTo(null)
    }
}
