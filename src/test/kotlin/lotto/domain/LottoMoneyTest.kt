package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMoneyTest {

    @Test
    fun `금액은 양수여야 한다`() {
        assertThrows<IllegalArgumentException> { LottoMoney(-123) }
    }

    @Test
    fun `금액이 1000원 단위여야 한다`() {
        assertThrows<IllegalArgumentException> { LottoMoney(123) }
    }
}
