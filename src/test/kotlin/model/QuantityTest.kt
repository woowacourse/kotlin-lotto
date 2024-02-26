package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class QuantityTest {
    @Test
    fun `수량은 0 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> {
            Quantity(-1)
        }
    }

    @Test
    fun `수량끼리 더한다`() {
        var quantity = Quantity()
        quantity += Quantity(1)
        assertThat(quantity).isEqualTo(Quantity(1))
    }

    @Test
    fun `수량끼리 뺀다`() {
        var quantity = Quantity(10)
        quantity -= Quantity(3)
        assertThat(quantity).isEqualTo(Quantity(7))
    }

    @Test
    fun `문자열로부터 수량을 만든다`() {
        val quantity = Quantity.from("20")
        assertThat(quantity.count).isEqualTo(20)
    }
}
