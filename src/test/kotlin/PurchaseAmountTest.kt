import domain.value.PurchaseAmount
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 100, 200, 3000, 45])
    fun `구매 금액은 0 이상의 숫자이다`(purchaseAmount: Int) {
        assertDoesNotThrow {
            PurchaseAmount(purchaseAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-100, -1000, -45])
    fun `구매 금액이 0 이상이 아니면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmount(purchaseAmount)
        }
    }
}
