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

    @ValueSource(ints = [-1, -30, -50, -1000000000])
    @ParameterizedTest
    fun `구입 금액은 0이상의 수이다`(value: Int) {
        assertThrows<IllegalArgumentException> { PurchaseMoney(value) }
    }

    @ValueSource(ints = [100_000_000, 200_000, 100_001])
    @ParameterizedTest
    fun `구입 금액은 10만 이하의 수이다`(value: Int) {
        assertThrows<IllegalArgumentException> { PurchaseMoney(value) }
    }
}
