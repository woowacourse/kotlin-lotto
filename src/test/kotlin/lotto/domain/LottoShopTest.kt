package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoShopTest {
    @Test
    fun `구입금액만큼 로또를 생성한다`() {
        val wantLottoCount = 5
        val pay = wantLottoCount * Lotto.PRICE
        val lottos: List<Lotto> = LottoShop().buyLottos(pay)
        assertThat(lottos.size).isEqualTo(wantLottoCount)
    }
}
