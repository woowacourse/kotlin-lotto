package lotto.domain

import lotto.util.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    val number: List<Int> = listOf(1, 2, 3, 4, 5, 6)
    val winningNumber: List<Int> = listOf(3, 4, 5, 6, 7, 8)

    @Test
    fun `6개 숫자를 넣으면 일치한 숫자의 개수를 알 수 있다`() {
        val lottoGame = LottoResult(winningNumber, 1)

        assertThat(lottoGame.compareLotto(number)).isEqualTo(4)
    }

    @Test
    fun `보너스 번호가 일치하면 true를 반환한다`() {
        val lottoGame = LottoResult(winningNumber, 1)

        assertThat(lottoGame.checkBonusNumber(number)).isTrue()
    }

    @Test
    fun `보너스 번호가 일치하지 않으면 false를 반환한다`() {
        val lottoGame = LottoResult(winningNumber, 45)

        assertThat(lottoGame.checkBonusNumber(number)).isFalse()
    }

    @Test
    fun `구입한 로또들의 당첨 통계를 알 수 있다`() {
        val number1: List<Int> = listOf(1, 2, 3, 4, 5, 6) // 5개 매치
        val number2: List<Int> = listOf(2, 3, 4, 5, 6, 7) // 6개 매치
        val number3: List<Int> = listOf(3, 4, 5, 6, 7, 45) // 5개 + 보너스볼 매치
        val lottos: List<Lotto> = listOf(Lotto(number1), Lotto(number2), Lotto(number3))

        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 45

        val lottoGame = LottoResult(winningNumber, winningBonusNumber)
        val winningStats = lottoGame.matchLotto(lottos)

        val correctWinningStats: Map<Rank, Int> =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 1,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
            )
        assertThat(winningStats).isEqualTo(correctWinningStats)
    }

    @Test
    fun `당첨 통계로 당첨 금액을 계산한다`() {
        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 45

        val lottoResult = LottoResult(winningNumber, winningBonusNumber)

        val winningStats = mapOf(Rank.FIRST to 1, Rank.SECOND to 1, Rank.THIRD to 1, Rank.FOURTH to 1, Rank.FIFTH to 1)

        assertThat(lottoResult.calculatePrize(winningStats)).isEqualTo(2_031_555_000)
    }

    @Test
    fun `당첨 금액과 구입 금액으로 수익률을 계산한다`() {
        val totalPrize: Long = 5_000
        val purchaseAmount: Int = 14_000

        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 45

        val lottoResult = LottoResult(winningNumber, winningBonusNumber)

        assertThat(lottoResult.calculateProfit(totalPrize, purchaseAmount)).isEqualTo(totalPrize / purchaseAmount.toDouble())
    }
}
