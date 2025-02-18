package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    fun `구입 금액이 천원 단위가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Amount(1100)
        }
    }

    @Test
    fun `구입 금액이 천원 미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Amount(900)
        }
    }

    @Test
    fun `구입 금액을 로또 가격으로 나누면 로또 수량이다`() {
        val amount = Amount(10000)
        assertThat(amount.getLottoQuantity()).isEqualTo(10000 / Amount.LOTTO_PRICE)
    }
}
