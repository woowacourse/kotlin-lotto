package lotto.domain.purchasecount

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualPurchaseCountTest {

    @Test
    fun `수동 구입 로또의 수는 음수가 아니여야한다`() {
        assertThrows<IllegalArgumentException> { ManualPurchaseCount(-3) }
    }
}
