package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PurchaseAmountTest {
    @Test
    internal fun `로또 개수가 구매한 양보다 적은지 확인`() {
        val purchaseAmount = PurchaseAmount(2000)

        assertAll("로또 개수 확인",
            {assertThat(purchaseAmount.isUnderCountOfLottoTicket(1)).isTrue()},
            {assertThat(purchaseAmount.isUnderCountOfLottoTicket(2)).isFalse()}
        )
    }
}
