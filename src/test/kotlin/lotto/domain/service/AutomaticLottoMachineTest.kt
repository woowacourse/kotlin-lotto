package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutomaticLottoMachineTest {
    @Test
    fun `로또 수량 5개에 대해 5장의 로또를 발급한다`() {
        // given
        val lottoMachine = AutomaticLottoMachine()
        val lottoCount = LottoCount(5)

        // when
        val lottos: List<Lotto> = lottoMachine.generate(lottoCount)

        // then
        assertThat(lottos.size).isEqualTo(5)
    }
}
