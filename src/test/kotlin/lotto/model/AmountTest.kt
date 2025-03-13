package lotto.model

import lotto.domain.model.Amount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AmountTest {
    @Test
    fun `소지한 금액으로 로또 구매 가능한 개수를 반환한다`() {
        val amount = Amount.valueOfOrNull(10000)
        assertThat(amount).isNotNull
        assertThat(amount?.getCount(1000)).isEqualTo(10)
    }

    @Test
    fun `소지한 금액이 음수면 null을 반환한다`() {
        val amount = Amount.valueOfOrNull(-1)
        assertThat(amount).isNull()
    }

    @Test
    fun `구매 후 남은 금액을 가진 Amount 객체를 반환한다`() {
        val amount = Amount.valueOfOrNull(10000)?.paymentOrNull(7000)
        assertThat(amount?.money).isEqualTo(3000)
    }

    @Test
    fun `소지한 금액보다 큰 금액을 지불하면 null을 반환한다`() {
        val amount = Amount.valueOfOrNull(10000)?.paymentOrNull(17000)
        assertThat(amount).isNull()
    }
}
