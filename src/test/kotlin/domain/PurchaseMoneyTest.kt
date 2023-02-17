package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PurchaseMoneyTest {
    @Test
    fun `1000원 미만의 돈이면 에러를 반환한다`() {
        assertThrows<IllegalArgumentException> { PurchaseLottoMoney(500) }
        assertThrows<IllegalArgumentException> { PurchaseLottoMoney(999) }
    }

    @Test
    fun `1000원 이상의 금액이면 에러를 반환하지 않는다`() {
        assertDoesNotThrow { PurchaseLottoMoney(1000) }
        assertDoesNotThrow { PurchaseLottoMoney(1500) }
    }

    @Test
    fun `로또 구입 금액으로 구매 가능한 최대 로또의 개수가 일치하는지 검사`() {
        assertThat(PurchaseLottoMoney(1500).purchaseCount).isEqualTo(1)
        assertThat(PurchaseLottoMoney(2000).purchaseCount).isEqualTo(2)
    }
}
