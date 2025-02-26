package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoStoreTest {
    private val store = LottoStore()

    @Test
    fun `자동 로또 발매기를 통해 로또 5장을 발급한다`() {
        // given
        val lottoMachine = AutomaticLottoMachine()
        val lottoCount = LottoCount(5)

        // when
        val lottos: List<Lotto> = store.publish(lottoMachine, lottoCount)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(5)
    }

    @Test
    fun `수동 로또 발매기를 통해 로또 1장을 발급한다`() {
        // given
        val lottoMachine = ManualLottoMachine()
        val lottoCount = LottoCount(1)
        val lottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))

        // when
        val lottos: List<Lotto> = store.publish(lottoMachine, lottoCount, lottoNumbers)

        // then
        Assertions.assertThat(lottos.size).isEqualTo(1)
    }
}
