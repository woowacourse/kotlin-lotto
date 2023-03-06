package model

import model.Count.Companion.ERROR_MINUS_VALUE
import model.Count.Companion.ERROR_PURCHASE_LIMIT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CountTest {

    @Test
    fun `-2장의 수동로또를 구입한다`() {
        // given
        val money = Money(10000)
        val lottoCount = -2

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_MINUS_VALUE) {
            Count(money, lottoCount)
        }
    }

    @Test
    fun `10000원을 지불하고, 12장의 수동로또를 구입한다`() {
        // given
        val money = Money(10000)
        val manualLottoCount = 12

        // when, then
        assertThrows<IllegalArgumentException>(ERROR_PURCHASE_LIMIT) {
            Count(money, manualLottoCount)
        }
    }
}
