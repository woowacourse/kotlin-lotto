package lotto.entity


import lotto.controller.World
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {
    @Test
    fun `구입금액이 로또 가격인 1000보다 작은 500일 경우 예외가 발생한다`() {
        // given
        val purchaseMoney = 500

        // when
        var thrown = assertThrows<IllegalArgumentException> { PurchaseMoney(purchaseMoney) }
        var except = String.format(PurchaseMoney.ERROR_MESSAGE_PURCHASE_LESS_THAN_LOTTO, purchaseMoney)

        // then
        assertThat(thrown.message).isEqualTo(except)
    }
}