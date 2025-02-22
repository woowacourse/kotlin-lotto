package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoMachine
import lotto.domain.value.LottoPayInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 구매 수량에 해당하는 만큼의 로또를 발급한다`() {
        val lottoPayInfo = LottoPayInfo(5000)
        val lottoMachine = LottoMachine()
        val lottos: List<Lotto> = lottoMachine.generateLottos(lottoPayInfo)
        assertThat(lottos.size).isEqualTo(5)
    }
}
