package lotto.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseMoneyTest {
    @Test
    fun `구입금액이 로또 가격인 1000보다 작은 500일 경우 예외가 발생한다`() {
        // given
        val purchaseMoney = 500

        // when
        val thrown = assertThrows<IllegalArgumentException> { PurchaseMoney(purchaseMoney) }
        val except = "구입 금액이 로또 가격보다 적습니다. 입력된 구입 금액은 %d원 입니다.".format(purchaseMoney)

        // then
        assertThat(thrown.message).isEqualTo(except)
    }
}
