package lotto.service

import lotto.model.PurchaseQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ServiceTest {

    @ParameterizedTest
    @CsvSource("1000,1", "10000,10")
    fun `구매할 수 있는 로또의 개수 계산한다`(purchasePrice: Int, amount: Int) {
        val purchaseQuantity = PurchaseQuantity(purchasePrice)
        assertThat(purchaseQuantity.amount).isEqualTo(amount)
    }
}
