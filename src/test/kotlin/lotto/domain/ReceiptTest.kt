package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ReceiptTest {
    @Test
    fun `구매금액과 수동 로또 개수를 가진다`() {
        val receipt = Receipt(PurchaseAmount(5000), TicketCount(5))

        assertThat(receipt.purchase.amount).isEqualTo(5000)
        assertThat(receipt.manual.count).isEqualTo(5)
    }

    @Test
    fun `수동 로또 개수가 구입 로또 개수를 초과하지 않는지 확인한다`() {
        assertDoesNotThrow { Receipt(PurchaseAmount(5000), TicketCount(4)) }
        assertThrows<IllegalArgumentException> { Receipt(PurchaseAmount(5000), TicketCount(6)) }
    }

    @Test
    fun `자동 로또 개수를 계산한다`() {
        val receipt = Receipt(PurchaseAmount(5000), TicketCount(2))
        assertThat(receipt.auto.count).isEqualTo(3)
    }
}
