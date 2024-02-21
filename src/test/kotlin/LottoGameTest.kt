import lotto.model.Lotto
import lotto.model.LottoPrize
import lotto.model.UserPrize
import lotto.model.WinningNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    private val lotto = Lotto(setOf(1, 2, 3, 4, 5, 6))
    private val winningNumber = WinningNumber(
        lotto = lotto,
        bonusNumber = 5
    )

    @Test
    fun `보너스 매치`() {
        assertThat(lotto.matchBonusNumber(winningNumber)).isTrue()
    }

    @Test
    fun `당첨 계산`() {
        assertThat(lotto.matchCount(winningNumber)).isEqualTo(6)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨)`() {
        assertThat(lotto.findRanking(0, true)).isEqualTo(LottoPrize.BOOM)
        assertThat(lotto.findRanking(1, true)).isEqualTo(LottoPrize.BOOM)
        assertThat(lotto.findRanking(2, true)).isEqualTo(LottoPrize.BOOM)
        assertThat(lotto.findRanking(3, true)).isEqualTo(LottoPrize.FIFTH)
        assertThat(lotto.findRanking(4, true)).isEqualTo(LottoPrize.FOURTH)
        assertThat(lotto.findRanking(5, true)).isEqualTo(LottoPrize.SECOND)
        assertThat(lotto.findRanking(6, true)).isEqualTo(LottoPrize.FIRST)
    }

    @Test
    fun `당첨금 계산 (보너스 미당첨)`() {
        assertThat(lotto.findRanking(0, false)).isEqualTo(LottoPrize.BOOM)
        assertThat(lotto.findRanking(1, false)).isEqualTo(LottoPrize.BOOM)
        assertThat(lotto.findRanking(2, false)).isEqualTo(LottoPrize.BOOM)
        assertThat(lotto.findRanking(3, false)).isEqualTo(LottoPrize.FIFTH)
        assertThat(lotto.findRanking(4, false)).isEqualTo(LottoPrize.FOURTH)
        assertThat(lotto.findRanking(5, false)).isEqualTo(LottoPrize.THIRD)
        assertThat(lotto.findRanking(6, false)).isEqualTo(LottoPrize.FIRST)
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
        assertThat(0.357).isEqualTo(UserPrize(mapOf(LottoPrize.FIRST to 1)).prizeRateCalculate(5000, 14))
    }
}
