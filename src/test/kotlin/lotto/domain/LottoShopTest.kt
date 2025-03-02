package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoShopTest {
    @Test
    fun `구입금액만큼 로또를 생성한다`() {
        val expectedLottoCount = 5
        val pay: Int = Lotto.PRICE * expectedLottoCount
        val lottoShop = LottoShop(pay)
        val boughtLottos: List<Lotto> = lottoShop.buyLottos()
        assertThat(boughtLottos.size).isEqualTo(expectedLottoCount)
    }
}
