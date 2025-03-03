package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    @Test
    fun `로또 번호 6개에 대해 1장의 로또를 발급한다`() {
        // given
        val lottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val lottoMachine = ManualLottoMachine(lottoNumbers)
        val lottoCount = LottoCount(1)

        // when
        val lottos: List<Lotto> = lottoMachine.generate(lottoCount)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(1)
    }
}
