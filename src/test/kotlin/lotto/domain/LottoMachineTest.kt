package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `구입할 로또의 개수를 입력하면 입력한 개수만큼 로또를 반환한다`() {
        val lottoMachine = LottoMachine()
        assertThat(lottoMachine.buyLottos(5).size).isEqualTo(5)
    }
}
