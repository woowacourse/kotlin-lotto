package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderTest {
    @ParameterizedTest
    @ValueSource(ints = [999, 0, -1000])
    fun `구입 금액이 로또 한 장의 가격보다 낮을 수 없다`(input: Int) {
        assertThrows<IllegalArgumentException> { Order(Order.Payment(input), Order.Quantity(0)) }
    }

    @Test
    fun `수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없다`() {
        assertThrows<IllegalArgumentException> { Order(Order.Payment(1000), Order.Quantity(2)) }
    }
}
