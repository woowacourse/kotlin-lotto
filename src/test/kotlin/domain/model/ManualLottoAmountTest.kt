package domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoAmountTest {
    @Test
    fun `수동 로또 개수가 총 구입하려는 로또 개수보다 많으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException>(
            message = "수동 구매 개수가 구입 가능한 로또 개수를 초과했습니다.",
            executable = {
                ManualLottoAmount(3, PurchasePrice(2000))
            },
        )
    }
}
