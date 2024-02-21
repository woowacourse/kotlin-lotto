package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CashierTest {
    @Test
    fun name() {
        val result = Cashier().toTicketQuantity(Money(10000))
        assertThat(result).isEqualTo(10)
    }
}
