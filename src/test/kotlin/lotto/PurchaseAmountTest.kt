package lotto

import lotto.model.PurchaseAmount
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    private val purchaseAmount = PurchaseAmount()

    @ParameterizedTest
    @ValueSource(strings = ["", "채123채", "!000", "10o0", "1ㅇㅇㅇ"])
    fun `구입금액이 숫자가 아닌 경우 예외가 발생한다`(invalidAmount: String) {
        assertThrows<IllegalArgumentException> { purchaseAmount.getAmount(invalidAmount) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1000", "0", "100", "999"])
    fun `구입금액이 1000이상이 아닌경우 예외가 발생한다`(invalidAmount: String) {
        assertThrows<IllegalArgumentException> { purchaseAmount.getAmount(invalidAmount) }
    }
}
