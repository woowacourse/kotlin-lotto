import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPrize
import lotto.model.UserPrize
import lotto.model.WinningNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    private val firstPrizeLotto = Lotto(LottoNumber(setOf(1, 2, 3, 4, 5, 6)))
    private val secondPrizeLotto = Lotto(LottoNumber(setOf(1, 2, 3, 4, 5, 7)))
    private val thirdPrizeLotto = Lotto(LottoNumber(setOf(1, 2, 3, 4, 5, 8)))
    private val boomPrizeLotto = Lotto(LottoNumber(setOf(7, 9, 10, 11, 12, 13)))
    private val correctWinningNumber = WinningNumber(
        lotto = Lotto(LottoNumber(setOf(1, 2, 3, 4, 5, 6))),
        bonusNumber = 7
    )
    private val wrongWinningNumber = WinningNumber(
        lotto = Lotto(LottoNumber(setOf(1, 2, 3, 4, 5, 6))),
        bonusNumber = 45
    )

    @Test
    fun `보너스 매치`() {
        assertThat(boomPrizeLotto.matchBonusNumber(correctWinningNumber)).isTrue()
    }

    @Test
    fun `당첨 계산`() {
        assertThat(firstPrizeLotto.matchCount(correctWinningNumber)).isEqualTo(6)
        assertThat(firstPrizeLotto.matchCount(wrongWinningNumber)).isEqualTo(6)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨)`() {
        assertThat(
            secondPrizeLotto.findRanking(correctWinningNumber).getPrice()
        ).isEqualTo(LottoPrize.SECOND.getPrice())
    }

    @Test
    fun `당첨금 계산 (보너스 미당첨)`() {
        assertThat(thirdPrizeLotto.findRanking(wrongWinningNumber).getPrice()).isEqualTo(LottoPrize.THIRD.getPrice())
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
                14000.0
            )
        )
    }
}
