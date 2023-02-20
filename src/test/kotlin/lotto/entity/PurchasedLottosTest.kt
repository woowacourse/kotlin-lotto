package lotto.entity

import lotto.model.Rank
import lotto.model.SequentialLottoNumberGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchasedLottosTest {
    @Test
    fun `구매한 로또 (1,2,3,4,5,6)이 당첨 로또 (1,2,3,4,5,6)과 일치하면 1등이다`() {
        // given
        val lottos = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val purchaseMoney = PurchaseMoney(1000)
        val lottoNumberGenerator = SequentialLottoNumberGenerator(lottos)
        val purchasedLottos = PurchasedLottos.from(purchaseMoney, lottoNumberGenerator)
        val winLotto = WinLotto(lottos[0], LottoNumber.from(7))

        // when
        val winStatistics = purchasedLottos.makeWinStatistics(winLotto)

        // then
        assertThat(winStatistics.value[0]).isEqualTo(Rank.FIRST)
    }
}
