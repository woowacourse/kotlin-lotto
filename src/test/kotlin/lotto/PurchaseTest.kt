package lotto

import lotto.domain.Purchase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PurchaseTest {
    @Test
    fun `구입 금액은 1000원 이상이어야 한다`() {
        val amountOfPurchase = 500
        assertThrows<IllegalArgumentException> { Purchase(amountOfPurchase) }
    }

    @Test
    fun `로또는 1000원 단위로 구입할 수 있다`() {
        val amountOfPurchase = 1000
        assertDoesNotThrow { Purchase(amountOfPurchase) }
    }

    @Test
    fun `구입 금액에 맞는 로또 개수를 계산할 수 있다`() {
        val amountOfPurchase = 10000
        assertThat(Purchase(amountOfPurchase).calculateAmountOfLottos()).isEqualTo(10)
    }

    @Test
    fun `1000원으로 나누어 떨어지지 않는 구입 금액에 따른 로또 개수를 계산할 수 있다`() {
        val amountOfPurchase = 9500
        assertThat(Purchase(amountOfPurchase).calculateAmountOfLottos()).isEqualTo(9)
    }

    @Test
    fun `구입한 로또의 개수에 맞는 실제 구매 금액을 계산할 수 있다`() {
        val amountOfPurchase = 19500
        assertThat(Purchase(amountOfPurchase).getPrice()).isEqualTo(19000)
    }
}
