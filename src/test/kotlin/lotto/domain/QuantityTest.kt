package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class QuantityTest {
    @Test
    fun `로또의 개수는 0 미만일 수 없다`() {
        assertThrows<IllegalArgumentException> { Order.Quantity(-1) }
    }
}
