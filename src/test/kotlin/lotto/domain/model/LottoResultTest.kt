package lotto.domain.model

import lotto.domain.service.LottoCalculator
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoResultTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val winningNumbers = Lotto.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(45)
        winningLotto = WinningLotto(winningNumbers, bonusNumber)
    }

    @Test
    fun `14장의 로또 중 1장이 3등일 때 당첨 수익률을 계산한다`() {
        // give
        val purchaseAmount = PurchaseAmount(14000)
        val thirdRankLotto = Lotto.of(4, 5, 6, 7, 8, 9)
        val missRankLotto = Lotto.of(11, 12, 13, 14, 15, 16)
        val lottos = mutableListOf(thirdRankLotto)
        repeat(13) { lottos.add(missRankLotto) }

        // when
        val lottoCalculator = LottoCalculator()
        val lottoResult = lottoCalculator.calculate(winningLotto, lottos)
        val earningRate = lottoResult.getEarningRate(purchaseAmount)

        // then
        val expected = (5_000).toDouble() / purchaseAmount.amount
        assertThat(earningRate).isEqualTo(expected)
    }
}
