package lotto.domain

import lotto.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CashierTest {
    @Test
    fun `입력된 금액을 계산해 적절한 티켓 수량으로 반환하는지 테스트`() {
        val result = Cashier().toTicketQuantity(Money(10000))
        assertThat(result).isEqualTo(10)
    }
}
