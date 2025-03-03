package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    fun `구입 금액을 로또 가격으로 나누면 로또 수량과 같다`() {
        val amount = Amount(10000)
        assertThat(amount.getQuantity()).isEqualTo(10000 / 1000)
    }

    @Test
    fun `구입 금액은 최소 주문 금액보다 크다`() {
        val exception = assertThrows<IllegalArgumentException> { Amount(999) }
        assertThat(exception.message).isEqualTo("[ERROR] 구입 금액이 최소 금액보다 작습니다.")
    }
}
