import lotto.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    private val lottos = Lottos(
        lottos = listOf(
            Lotto(setOf(12, 11, 7, 8, 9, 10)),
            Lotto(setOf(1, 11, 7, 8, 9, 10)),
            Lotto(setOf(1, 2, 7, 8, 9, 10)),
            Lotto(setOf(1, 2, 3, 7, 8, 9)),
            Lotto(setOf(1, 2, 3, 4, 7, 8)),
            Lotto(setOf(1, 2, 3, 4, 5, 8)),
            Lotto(setOf(1, 2, 3, 4, 5, 7)),
            Lotto(setOf(1, 2, 3, 4, 5, 6))
        )
    )
    private val winningNumberCorrect = WinningNumber(
        lotto = Lotto(setOf(1,2,3,4,5,6)),
        bonusNumber = 7
    )
    private val winningNumberWrong= WinningNumber(
        lotto = Lotto(setOf(1,2,3,4,5,6)),
        bonusNumber = 45
    )

    @Test
    fun `보너스 매치`() {
        assertThat(lottos.getLottos()[0].matchBonusNumber(winningNumberCorrect)).isTrue()
    }

    @Test
    fun `당첨 계산`() {
        assertThat(lottos.getLottos()[8].matchCount(winningNumberCorrect)).isEqualTo(6)
        assertThat(lottos.getLottos()[8].matchCount(winningNumberWrong)).isEqualTo(6)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨)`() {
        assertThat(lottos.getLottos()[0].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[1].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[2].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[3].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.FIFTH)
        assertThat(lottos.getLottos()[4].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.FOURTH)
        assertThat(lottos.getLottos()[6].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.SECOND)
        assertThat(lottos.getLottos()[7].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.FIRST)
    }

    @Test
    fun `당첨금 계산 (보너스 미당첨)`() {
        assertThat(lottos.getLottos()[0].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[1].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[2].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[3].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.FIFTH)
        assertThat(lottos.getLottos()[4].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.FOURTH)
        assertThat(lottos.getLottos()[5].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.THIRD)
        assertThat(lottos.getLottos()[7].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.FIRST)
    }

    @Test
    fun `수익 계산`() {
        assertThat(0).isEqualTo(UserPrize(mapOf(LottoPrize.BOOM to 1)).prizeCalculate())
        assertThat(5_000).isEqualTo(UserPrize(mapOf(LottoPrize.FIFTH to 1)).prizeCalculate())
        assertThat(50_000).isEqualTo(UserPrize(mapOf(LottoPrize.FOURTH to 1)).prizeCalculate())
        assertThat(1_500_000).isEqualTo(UserPrize(mapOf(LottoPrize.THIRD to 1)).prizeCalculate())
        assertThat(30_000_000).isEqualTo(UserPrize(mapOf(LottoPrize.SECOND to 1)).prizeCalculate())
        assertThat(2_000_000_000).isEqualTo(UserPrize(mapOf(LottoPrize.FIRST to 1)).prizeCalculate())
    }

    @Test
    fun `수익률 계산`() {
        assertThat(0.35714285714285715).isEqualTo(UserPrize(mapOf(LottoPrize.FIRST to 1)).prizeRateCalculate(5000, 14000.0))
    }
}
