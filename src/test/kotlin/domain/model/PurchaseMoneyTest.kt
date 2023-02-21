package domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {

    @Test
    fun `1000원 단위의 돈인 경우`() {
        assertDoesNotThrow {
            PurchaseMoney(15000)
        }
    }

    @Test
    fun `1000원 단위의 돈이 아닌 경우 예외가 발생`() {
        assertThrows<IllegalArgumentException> {
            PurchaseMoney(15500)
        }
    }

    @Test
    fun `0보다 작은 금액의 돈을 입력한 경우`() {
        assertThrows<IllegalArgumentException> {
            PurchaseMoney(-1000)
        }
    }
}
