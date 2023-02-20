package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {

    @Test
    fun `구입 금액이 로또 1장의 가격보다 적으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseMoney(100) }
    }
}
