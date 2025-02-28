package lotto.model

import lotto.Amount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AmountTest {
    @Test
    fun `소지한 돈을 로또 금액으로 나누면 로또 갯수가 리턴된다`() {
        val amount = Amount.createOrNull(10000)
        assertThat(amount).isNotNull
        assertThat(amount?.getCount(1000)).isEqualTo(10)
    }

    @Test
    fun `소지한 돈이 음수이면 null이 생성된다`() {
        val amount = Amount.createOrNull(-1)
        assertThat(amount).isEqualTo(null)
    }

    @Test
    fun `소지한 돈에 구입 금액을 지불하면 남은 돈을 가진 객채가 생성된다`() {
        var amount = Amount.createOrNull(10000)
        amount = amount?.paymentOrNull(7000)
        assertThat(amount?.money).isEqualTo(3000)
    }

    @Test
    fun `소지한 돈 이상의 구입 금액을 지불하면 null가 생성된다`() {
        var amount = Amount.createOrNull(10000)
        amount = amount?.paymentOrNull(17000)
        assertThat(amount?.money).isEqualTo(null)
    }
}
