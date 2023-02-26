package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReceiptTest {
    @Test
    fun `구매금액과 수동 로또 개수를 가진다`() {
        val receipt = Receipt(PurchaseAmount(5000), TicketCount(10))

        assertThat(receipt.purchase.amount).isEqualTo(5000)
        assertThat(receipt.manual.count).isEqualTo(10)
    }
}
