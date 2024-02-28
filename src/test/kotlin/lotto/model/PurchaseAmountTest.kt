package lotto.model

import lotto.model.PurchaseAmount.Companion.TICKET_PRICE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PurchaseAmountTest {
    @Test
    fun `구입 금액이 로또 한 장 가격보다 작다면, 구매 실패 이다`() {
        assertThrows<IllegalArgumentException> { PurchaseAmount(0) }
    }

    @Test
    fun `구입 금액이 로또 한 장 가격과 같거나 이상이라면, 구매 가능하다`() {
        assertDoesNotThrow { PurchaseAmount(TICKET_PRICE) }
    }

    @Test
    fun `구입 금액에 따라 발행 가능한 로또 개수를 알려준다`() {
        val result = PurchaseAmount(1500).getTotalNumberOfLotto()
        assertThat(result).isEqualTo(1)
    }
}
