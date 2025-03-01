package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {
    @Test
    fun `수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없다`() {
        val payment = Order.Payment(1000)
        val manualQuantity = Order.Quantity(2)
        val manualNumbersList: List<List<Int>> =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
            )
        assertThrows<IllegalArgumentException> { Order(payment, manualQuantity, manualNumbersList) }
    }

    @Test
    fun `수동으로 구매할 로또의 개수와 입력된 로또 번호 목록의 크기는 다를 수 없다`() {
        val payment = Order.Payment(2000)
        val manualQuantity = Order.Quantity(2)
        val manualNumbersList: List<List<Int>> = listOf(listOf(1, 2, 3, 4, 5, 6))
        assertThrows<IllegalArgumentException> { Order(payment, manualQuantity, manualNumbersList) }
    }
}
