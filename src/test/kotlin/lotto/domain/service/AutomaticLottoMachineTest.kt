package lotto.domain.service

import lotto.domain.model.Lotto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AutomaticLottoMachineTest {
    private val lottoMachine = AutomaticLottoMachine()

    @Test
    fun `로또 수량 5개에 대해 5장의 로또를 발급한다`() {
        // given
        val lottoCount = 5

        // when
        val lottos: List<Lotto> = lottoMachine.generate(lottoCount)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(5)
    }
}
