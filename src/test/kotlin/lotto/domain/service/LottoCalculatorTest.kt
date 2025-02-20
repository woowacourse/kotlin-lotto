package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.PurchaseDetail
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoCalculatorTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val winningNumbers = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber(45)
        winningLotto = WinningLotto(winningNumbers, bonusNumber)
    }

    @Test
    fun `로또 1등이 한번 당첨되면 1등 당첨 횟수는 1이다`() {
        val firstRankNumbers = (1..6).map { LottoNumber(it) }
        val lottos = listOf(Lotto(firstRankNumbers))

        val purchaseAmount = PurchaseAmount(1000)
        val purchaseDetail = PurchaseDetail(purchaseAmount, lottos)
        val lottoCalculator = LottoCalculator()
        val winningStats = lottoCalculator.calculateLottoResult(winningLotto, purchaseDetail)

        assertThat(winningStats.winningStats[Rank.FIRST]).isEqualTo(1)
    }

    @Test
    fun `당첨되지 않은 로또가 1개이면 미당첨 횟수는 1이다`() {
        val firstRankNumbers = (1..6).map { LottoNumber(it) }
        val missRankNumbers = (11..16).map { LottoNumber(it) }
        val lottos = listOf(Lotto(firstRankNumbers), Lotto(missRankNumbers))

        val purchaseAmount = PurchaseAmount(2000)
        val purchaseDetail = PurchaseDetail(purchaseAmount, lottos)
        val lottoCalculator = LottoCalculator()
        val winningStats = lottoCalculator.calculateLottoResult(winningLotto, purchaseDetail)

        assertThat(winningStats.winningStats[Rank.MISS]).isEqualTo(1)
    }
}
