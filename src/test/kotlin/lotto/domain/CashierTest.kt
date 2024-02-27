package lotto.domain

import lotto.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CashierTest {
    @Test
    fun `입력 금액이 10000원, 물건 가격이 1000원일 때 수량을 10개 반환하는지 테스트`() {
        val result = Cashier().calculateQuantity(Money(10000), 1000)
        assertThat(result).isEqualTo(10)
    }
}
