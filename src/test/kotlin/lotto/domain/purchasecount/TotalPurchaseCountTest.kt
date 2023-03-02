package lotto.domain.purchasecount

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TotalPurchaseCountTest {
    @Test
    fun `수동 구입 로또의 수는 구입개수를 넘을수 없다`() {
        assertThrows<IllegalArgumentException> { TotalPurchaseCount.from(10, 15) }
    }
}
