package lotto

import lotto.domain.value.PurchaseAmount
import lotto.domain.value.PurchaseQuantity
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 2000, 3000, 4000, 5000])
    fun `구입 금액은 1000원 단위이다`(amount: Int) {
        assertDoesNotThrow {
            val purchaseAmount = PurchaseAmount(amount)
            PurchaseQuantity(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1100, 2100, 3100, 4100, 5100])
    fun `구입 금액은 1000원 단위가 아니면 예외가 발생한다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            val purchaseAmount = PurchaseAmount(amount)
            PurchaseQuantity(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 2000, 3000, 4000, 5000])
    fun `구입 금액은 1000원 이상이다`(amount: Int) {
        assertDoesNotThrow {
            val purchaseAmount = PurchaseAmount(amount)
            PurchaseQuantity(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1000, 100, 200, 300, 400, 500])
    fun `구입 금액은 1000원 이상이 아니면 예외가 발생한다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            val purchaseAmount = PurchaseAmount(amount)
            PurchaseQuantity(purchaseAmount)
        }
    }
}
