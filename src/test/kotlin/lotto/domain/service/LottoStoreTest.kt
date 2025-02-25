package lotto.domain.service

import lotto.domain.model.Lotto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoStoreTest {
    private val lottoStore = LottoStore()

    @Test
    fun `로또 자동 발매기를 통해 1장의 로또를 발급한다`() {
        // given
        val lottoMachine = AutomaticLottoMachine()
        val lottoCount = 1

        // when
        val lottos: List<Lotto> = lottoStore.sell(lottoMachine, lottoCount)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(1)
    }

    @Test
    fun `로또 수동 발매기를 통해 1장의 로또를 발급한다`() {
        // given
        val lottoMachine = ManualLottoMachine()
        val lottoCount = 1
        val lottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))

        // when
        val lottos: List<Lotto> = lottoStore.sell(lottoMachine, lottoCount, lottoNumbers)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(1)
    }
}
