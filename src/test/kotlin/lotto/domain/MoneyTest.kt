package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `로또 한 장보다 적은 돈은 돈으로 취급하지 않는다`() {
        val expectThrowAmount = Lotto.PRICE - 1
        assertThrows<IllegalArgumentException> { Money(expectThrowAmount) }
    }
}
