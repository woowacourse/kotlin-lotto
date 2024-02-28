package lotto

import lotto.model.PurchaseAmount
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    private val purchaseUnit = 1000
    private val smallPurchaseAmount = "999"

    @ParameterizedTest
    @ValueSource(strings = ["-1000", "0", "100", "999"])
    fun `구입금액이 1000이상이 아닌경우 예외가 발생한다`(invalidAmount: String) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(invalidAmount, purchaseUnit) }
    }

    @Test
    fun `구입금액이 purchaseUnit미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseAmount(smallPurchaseAmount, purchaseUnit) }
    }

    @Test
    fun `구입금액이 purchaseUnit이상인 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow { PurchaseAmount(purchaseUnit.toString(), purchaseUnit) }
    }
}
