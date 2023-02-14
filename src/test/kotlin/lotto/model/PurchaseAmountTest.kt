package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액을 가진다`(amount: Int) {
        val purchaseAmount = PurchaseAmount(amount)
        assertThat(purchaseAmount.amount).isEqualTo(amount)
    }
}