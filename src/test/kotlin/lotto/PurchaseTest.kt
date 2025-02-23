package lotto

import lotto.domain.Purchase
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
    fun `구입 금액에 맞는 로또 개수를 계산할 수 있다`() {
        val amountOfPurchase = 10000
        assertThat(Purchase(amountOfPurchase).calculateAmountOfLottos()).isEqualTo(10)
    }
}
