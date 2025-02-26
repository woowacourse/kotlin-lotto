package lotto.domain.model

import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank
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
    fun `14장의 로또 중 1장이 5등일 때 당첨 수익률을 계산한다`() {
        // give
        val purchaseAmount = PurchaseAmount(14000)
        val winningStats = mapOf(Rank.MISS to 13, Rank.FIFTH to 1)

        // when
        val lottoResult = LottoResult(winningStats)
        val earningRate = lottoResult.getEarningRate(purchaseAmount)

        // then
        val expected = (5000).toDouble() / purchaseAmount.amount
        assertThat(earningRate).isEqualTo(expected)
    }
}
