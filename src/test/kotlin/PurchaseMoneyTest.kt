import domain.model.PurchaseMoney
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseMoneyTest {

    @Test
    fun `0보다 작은 금액의 돈을 입력한 경우`() {
        assertThrows<IllegalArgumentException> {
            PurchaseMoney(-1000)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [990, 3500])
    fun `구입 금액이 1000원 단위가 아닌 경우 예외가 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            PurchaseMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 15000])
    fun `구입 금액이 0보다 크고 1000원 단위를 만족하는 경우`(money: Int) {
        assertDoesNotThrow {
            PurchaseMoney(money)
        }
    }
}
