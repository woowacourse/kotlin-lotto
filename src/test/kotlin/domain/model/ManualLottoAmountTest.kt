package domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoAmountTest {
    @Test
    fun `수동 로또 구매 개수는 총 구매 개수를 넘을 수 없다`() {
        val manualLottoAmount = 15
        val totalPurchaseAmount = 10

        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 구매 가능한 개수보다 구매하려는 개수가 더 많습니다.",
        ) {
            ManualLottoAmount(value = manualLottoAmount, totalPurchaseAmount = totalPurchaseAmount)
        }
    }
}
