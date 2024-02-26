import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPrize
import lotto.model.Lottos
import lotto.model.UserPrize
import lotto.model.WinningNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    private val lottos =
        Lottos(
            lottos =
                listOf(
                    Lotto(
                        setOf(
                            LottoNumber.of(12),
                            LottoNumber.of(11),
                            LottoNumber.of(7),
                            LottoNumber.of(8),
                            LottoNumber.of(9),
                            LottoNumber.of(10),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(11),
                            LottoNumber.of(7),
                            LottoNumber.of(8),
                            LottoNumber.of(9),
                            LottoNumber.of(10),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(2),
                            LottoNumber.of(7),
                            LottoNumber.of(8),
                            LottoNumber.of(9),
                            LottoNumber.of(10),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(2),
                            LottoNumber.of(3),
                            LottoNumber.of(7),
                            LottoNumber.of(8),
                            LottoNumber.of(9),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(2),
                            LottoNumber.of(3),
                            LottoNumber.of(4),
                            LottoNumber.of(7),
                            LottoNumber.of(8),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(2),
                            LottoNumber.of(3),
                            LottoNumber.of(4),
                            LottoNumber.of(5),
                            LottoNumber.of(8),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(2),
                            LottoNumber.of(3),
                            LottoNumber.of(4),
                            LottoNumber.of(5),
                            LottoNumber.of(7),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber.of(1),
                            LottoNumber.of(2),
                            LottoNumber.of(3),
                            LottoNumber.of(4),
                            LottoNumber.of(5),
                            LottoNumber.of(6),
                        ),
                    ),
                ),
        )
    private val winningNumberCorrect =
        WinningNumber(
            lotto =
                Lotto(
                    setOf(
                        LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
            bonusNumber = LottoNumber(7),
        )
    private val winningNumberWrong =
        WinningNumber(
            lotto =
                Lotto(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
            bonusNumber = LottoNumber(45),
        )

    @Test
    fun `보너스 매치`() {
        assertThat(lottos.getLottos()[0].matchBonusNumber(winningNumberCorrect)).isTrue()
    }

    @Test
    fun `당첨 계산`() {
        assertThat(lottos.getLottos()[7].matchCount(winningNumberCorrect)).isEqualTo(6)
        assertThat(lottos.getLottos()[7].matchCount(winningNumberWrong)).isEqualTo(6)
    }

    @Test
    fun `당첨금 계산`() {
        assertThat(lottos.getLottos()[0].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[1].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[2].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.BOOM)
        assertThat(lottos.getLottos()[3].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.FIFTH)
        assertThat(lottos.getLottos()[4].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.FOURTH)
        assertThat(lottos.getLottos()[7].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.FIRST)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨 and 미당첨)`() {
        assertThat(lottos.getLottos()[5].findRanking(winningNumberWrong)).isEqualTo(LottoPrize.THIRD)
        assertThat(lottos.getLottos()[6].findRanking(winningNumberCorrect)).isEqualTo(LottoPrize.SECOND)
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
