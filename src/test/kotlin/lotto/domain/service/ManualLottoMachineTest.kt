package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    private val lottoMachine = ManualLottoMachine()

    @Test
    fun `로또 번호 6개에 대해 1장의 로또를 발급한다`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val lottoNumbers = listOf(numbers)
        val lottoOrder = LottoOrder(1, lottoNumbers)

        // when
        val lottos: List<Lotto> = lottoMachine.generate(lottoOrder)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(1)
    }
}
