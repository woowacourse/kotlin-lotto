package lotto

import lotto.model.LottoMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `발행해야 할 로또 개수 만큼 로또를 발행한다`() {
        val lottoCount = 5

        val lottos = lottoMachine.createLottos(5)

        assertThat(lottoCount).isEqualTo(lottos.size)
    }
}
