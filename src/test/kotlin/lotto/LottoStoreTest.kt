package lotto

import lotto.model.LottoStore
import lotto.model.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {
    @Test
    fun `로또를 5개 발행한다`() {
        val lottoStore = LottoStore()
        val purchaseCount = 5
        assertThat(lottoStore.getTickets(purchaseCount, RandomLottoGenerator()).size).isEqualTo(5)
    }
}
