package lotto.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {

    @Test
    fun `구입 금액이 로또 1장의 가격보다 적으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseMoney(100) }
    }

    @Test
    fun `구매 금액이 3000원이고 로또 1장의 가격이 1000원일 때 로또를 3장 구매할 수 있다`() {
        val actual = PurchaseMoney(3000).getPurchaseLottoCount()
        assertThat(actual).isEqualTo(3)
    }
}
