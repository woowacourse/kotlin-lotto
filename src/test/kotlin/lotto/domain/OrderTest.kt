package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {
    val order = Order(14000)

    @Test
    fun `수동 로또 개수는 총 로또 개수를 초과할 수 없다`() {
        assertThrows<IllegalArgumentException> { order.setManualCount(15) }
    }

    @Test
    fun `구매 금액과 수동 로또 개수로 자동 로또 개수를 반환한다`() {
        order.setManualCount(3)
        assertThat(order.getAutoCount()).isEqualTo(11)
    }
}
