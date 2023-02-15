import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {

    @Test
    fun `0보다 작은 금액의 돈을 입력한 경우`() {
        assertThrows<IllegalArgumentException> {
            PurchaseMoney(-1000)
        }
    }
}
