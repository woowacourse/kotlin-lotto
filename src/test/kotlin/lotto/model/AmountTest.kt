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
    fun `소지한 돈이 0 이하이면 null이 생성된다`() {
        val amount = Amount.createOrNull(-1)
        assertThat(amount).isEqualTo(null)
    }
}
