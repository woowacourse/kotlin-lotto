import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.lottoNumberSetOf
import lotto.model.LottoPrize
import lotto.model.UserPrize
import lotto.model.WinningNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    private val firstPrizeLotto = Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 6))
    private val secondPrizeLotto = Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 7))
    private val thirdPrizeLotto = Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 8))
    private val winningNumberCorrect = WinningNumber(Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 6)), LottoNumber(7))
    private val winningNumberWrong = WinningNumber(Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 6)), LottoNumber(45))

    @Test
    fun `보너스 매치`() {
        // 보너스 번호 매치 로직을 WinningNumber 객체로 이동
        assertThat(winningNumberCorrect.matchBonusNumber(secondPrizeLotto)).isTrue()
    }

    @Test
    fun `당첨 계산`() {
        assertThat(winningNumberCorrect.matchCount(firstPrizeLotto)).isEqualTo(6)
        assertThat(winningNumberWrong.matchCount(firstPrizeLotto)).isEqualTo(6)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨 and 미당첨)`() {
        assertThat(thirdPrizeLotto.findRanking(winningNumberWrong)).isEqualTo(LottoPrize.THIRD)
        assertThat(secondPrizeLotto.findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.SECOND)
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
        assertThat(0.35714285714285715).isEqualTo(
            UserPrize(mapOf(LottoPrize.FIRST to 1)).prizeRateCalculate(
                5000,
                14000.0,
            ),
        )
    }
}
