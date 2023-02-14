package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {

    @Test
    fun `구입 금액은 천원 단위여야한다`() {
        assertThrows<IllegalArgumentException> { PurchaseMoney(100) }
    }
}
