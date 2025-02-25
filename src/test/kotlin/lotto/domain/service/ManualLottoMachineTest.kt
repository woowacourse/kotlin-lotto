package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    private val lottoMachine = ManualLottoMachine()

    @Test
    fun `로또 번호 6개에 대해 1장의 로또를 발급한다`() {
        // given
        val lottoCount = LottoCount(1)
        val lottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))

        // when
        val lottos: List<Lotto> = lottoMachine.generate(lottoCount, lottoNumbers)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(1)
    }
}
