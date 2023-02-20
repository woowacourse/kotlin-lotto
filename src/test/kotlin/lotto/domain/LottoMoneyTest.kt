package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMoneyTest {

    @Test
    fun `금액은 양수이다`() {
        assertThrows<IllegalArgumentException> { LottoMoney(-123) }
    }

    @Test
    fun `금액은 1000원 단위이다`() {
        assertThrows<IllegalArgumentException> { LottoMoney(123) }
    }
}
