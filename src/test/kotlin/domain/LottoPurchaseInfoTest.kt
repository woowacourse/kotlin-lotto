package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoPurchaseInfoTest() {
    @Test
    fun `수동 발급 개수가 구매 가능 개수보다 크면 에러 발생`() {
        assertThrows<IllegalArgumentException> {
            LottoPurchaseInfo(PurchaseLottoMoney(14000), 15)
        }
    }
}
