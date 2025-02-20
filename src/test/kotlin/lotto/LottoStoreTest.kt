package lotto

import lotto.model.LottoStore
import lotto.model.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {
    @Test
    fun `로또를 구입 갯수만큼 발행한다 `() {
        val lottoStore = LottoStore()
        val purchase = 5000
        assertThat(lottoStore.getTickets(purchase, RandomLottoGenerator()).size).isEqualTo(5)
    }
}
