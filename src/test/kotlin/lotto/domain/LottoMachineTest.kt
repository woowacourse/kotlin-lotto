package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 1장의 가격은 1000원이다`() {
        val lottoMachine = LottoMachine()
        assertThat(lottoMachine.price).isEqualTo(1_000)
    }
}
