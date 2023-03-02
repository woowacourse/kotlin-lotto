package lotto.domain.purchasecount

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AutoPurchaseCountTest {
    @Test
    fun `자동 구입 로또의 수는 음수가 아니여야한다`() {
        assertThrows<IllegalArgumentException> { PurchaseCount(-3) }
    }
}
