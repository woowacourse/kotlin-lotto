package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PurchaseAmountTest {
    @Test
    internal fun `로또 개수`() {
        val purchasePrice = PurchaseAmount(2000)

        assertThat(purchasePrice.countOfLottoTicket()).isEqualTo(2)
    }
}
