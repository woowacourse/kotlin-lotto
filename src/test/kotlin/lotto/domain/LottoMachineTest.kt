package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 1장의 가격은 1000원이다`() {
        val lottoMachine = LottoMachine()
        assertThat(lottoMachine.price).isEqualTo(1_000)
    }

    @Test
    fun `구입 금액을 넣으면 몇 개의 로또를 구매했는지 알 수 있다`() {
        val lottoMachine = LottoMachine()
        assertThat(lottoMachine.buyLottos(1_000).size).isEqualTo(1)
    }
}
