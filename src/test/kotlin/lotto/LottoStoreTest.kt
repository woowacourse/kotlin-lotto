package lotto

import lotto.model.LottoStore
import lotto.model.generator.ManualLottoGenerator
import lotto.model.generator.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {
    @Test
    fun `로또를 5개 발행한다`() {
        val lottoStore = LottoStore()
        val purchaseCount = 5
        assertThat(lottoStore.getTickets(purchaseCount, RandomLottoGenerator()).size).isEqualTo(5)
    }

    @Test
    fun `수동 로또를 2개 발행한다`() {
        val lottoStore = LottoStore()
        val manualCount = 2
        val manualLottoBundle = listOf(listOf(1, 2, 3, 4, 5, 6), listOf(3, 4, 5, 6, 7, 8))
        assertThat(lottoStore.getTickets(manualCount, ManualLottoGenerator(manualLottoBundle)).size).isEqualTo(2)
    }
}
