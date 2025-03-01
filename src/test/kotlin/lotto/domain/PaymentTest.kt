package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PaymentTest {
    @ParameterizedTest
    @ValueSource(ints = [999, 0, -1000])
    fun `구입 금액이 로또 한 장의 가격보다 낮을 수 없다`(input: Int) {
        assertThrows<IllegalArgumentException> { Order.Payment(input) }
    }
}
