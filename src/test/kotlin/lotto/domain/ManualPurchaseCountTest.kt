package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualPurchaseCountTest {

    @Test
    fun `수동 구입 로또의 수는 구입개수를 넘을수 없다`() {
        assertThrows<IllegalArgumentException> { ManualPurchaseCount.from(15, 10) }
    }

    @Test
    fun `수동 구입 로또의 수는 음수가 아니여야한다`() {
        assertThrows<IllegalArgumentException> { ManualPurchaseCount.from(-3, 10) }
    }
}
