package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseTest {
    @Test
    fun `로또는 1000원 단위로 구입할 수 있다`() {
        val amountOfPurchase = 10001
        assertThrows<IllegalArgumentException> { Purchase(amountOfPurchase) }
    }

    @Test
    fun `구입 금액에 해당하는 로또를 발급한다`() {
        val amountOfPurchase = 10000
        assertThat(Purchase(amountOfPurchase).getAmountOfLottos(amountOfPurchase)).isEqualTo(10)
    }
}
