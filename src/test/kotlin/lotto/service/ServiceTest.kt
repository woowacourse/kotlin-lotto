package lotto.service

import lotto.model.PurchaseQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class ServiceTest {

    @ParameterizedTest
    @CsvSource("1000,1", "10000,10")
    fun `구매할 수 있는 로또의 개수 계산한다`(purchasePrice: String, amount: Int) {
        val purchaseQuantity = PurchaseQuantity.create(purchasePrice)
        assertThat(purchaseQuantity.amount).isEqualTo(amount)
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["0", "-1000", "1000100", "1000abc", "2200000000"])
    fun `로또의 구입 금액을 검증한다`(purchasePrice: String) {
        assertThrows<IllegalArgumentException> {
            PurchaseQuantity.create(purchasePrice)
        }
    }

}
