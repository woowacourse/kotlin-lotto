package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PurchaseAmountTest {
    @Test
    internal fun `로또 개수가 구매한 양보다 적은지 확인`() {
        val purchaseAmount = PurchaseAmount(2000)

        assertAll("로또 개수 확인",
            { assertThat(purchaseAmount.isUnderCountOfLottoTicket(1)).isTrue() },
            { assertThat(purchaseAmount.isUnderCountOfLottoTicket(2)).isFalse() }
        )
    }

    @Test
    internal fun `수익률 계산`() {
        val purchaseAmount = PurchaseAmount(2000)

        assertAll("수익률 계산",
            { assertThat(purchaseAmount.calculateProfit(0.0)).isEqualTo(0.00) },
            { assertThat(purchaseAmount.calculateProfit(20.0)).isEqualTo(0.01) },
            { assertThat(purchaseAmount.calculateProfit(200.0)).isEqualTo(0.10) },
            { assertThat(purchaseAmount.calculateProfit(2000.0)).isEqualTo(1.00) },
            { assertThat(purchaseAmount.calculateProfit(20000.0)).isEqualTo(10.00) }
        )
    }
}
