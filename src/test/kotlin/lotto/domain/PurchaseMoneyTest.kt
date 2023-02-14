package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseMoneyTest {
    @ValueSource(ints = [100, 1011, 20001])
    @ParameterizedTest
    fun `구입 금액은 천원 단위여야한다`(value: Int) {
        assertThrows<IllegalArgumentException> { PurchaseMoney(value) }
    }
}
