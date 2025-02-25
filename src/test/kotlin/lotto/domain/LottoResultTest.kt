package lotto.domain

import lotto.util.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    val number1 = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()) // 5개 매치
    val number2 = Lotto(listOf(2, 3, 4, 5, 6, 7).map { LottoNumber(it) }.toSet()) // 6개 매치
    val number3 = Lotto(listOf(3, 4, 5, 6, 7, 45).map { LottoNumber(it) }.toSet())
    val lottos = listOf(number1, number2, number3)
    val winningNumber = Lotto(listOf(2, 3, 4, 5, 6, 7).map { LottoNumber(it) }.toSet())
    val winningBonusNumber = LottoNumber(45)
    val lottoResult = LottoResult(WinningLotto(winningNumber, winningBonusNumber))

    @Test
    fun `구입한 로또들의 당첨 통계를 알 수 있다`() {
        lottoResult.calculateWinningStats(lottos)

        val correctWinningStats: Map<Rank, Int> =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 1,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
            )
        assertThat(lottoResult.getWinningStats()).isEqualTo(correctWinningStats)
    }

    @Test
    fun `당첨 통계로 당첨 금액을 계산한다`() {
        lottoResult.calculateWinningStats(lottos)
        lottoResult.calculatePrize()
        assertThat(lottoResult.calculatePrize()).isEqualTo(2_031_500_000)
    }

    @Test
    fun `당첨 금액과 구입 금액으로 수익률을 계산한다`() {
        val totalPrize: Long = 5_000
        val purchaseAmount = 14000

        lottoResult.calculateProfit(totalPrize, purchaseAmount)

        assertThat(lottoResult.getProfitRate()).isEqualTo(totalPrize / purchaseAmount.toDouble())
    }
}
