package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AutomaticLottoMachineTest {
    private val lottoMachine = AutomaticLottoMachine()

    @Test
    fun `로또 수량 5개에 대해 5장의 로또를 발급한다`() {
        // given
        val lottoOrder = LottoOrder(5)

        // when
        val lottos: List<Lotto> = lottoMachine.generate(lottoOrder)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(5)
    }
}
