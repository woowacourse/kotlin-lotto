import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.lottoNumberSetOf
import lotto.model.LottoPrize
import lotto.model.UserPrize
import lotto.model.WinningNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoGameTest {
    @ParameterizedTest
    @CsvSource(
        "1, 2, 3, 4, 5, 6, 7, 6",
        "1, 2, 3, 4, 5, 7, 6, 5",
    )
    fun `당첨 계산`(
        vararg numbers: Int,
        bonus: Int,
        expected: Int,
    ) {
        val winningNumbers = Lotto(lottoNumberSetOf(*numbers.copyOfRange(0, 6)))
        val bonusNumber = LottoNumber(bonus)
        val lotto = Lotto(lottoNumberSetOf(*numbers))
        assertThat(WinningNumber(winningNumbers, bonusNumber).matchCount(lotto)).isEqualTo(expected)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨 and 미당첨)`() {
        assertThat(
            Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 7)).findRanking(
                WinningNumber(Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 6)), LottoNumber(45)),
            ),
        ).isEqualTo(LottoPrize.THIRD)
        assertThat(
            Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 7)).findRanking(
                WinningNumber(Lotto(lottoNumberSetOf(1, 2, 3, 4, 5, 6)), LottoNumber(7)),
            ),
        ).isEqualTo(LottoPrize.SECOND)
    }

    @ParameterizedTest
    @CsvSource(
        "BOOM, 0",
        "FIFTH, 5000",
        "FOURTH, 50000",
        "THIRD, 1500000",
        "SECOND, 30000000",
        "FIRST, 2000000000",
    )
    fun `수익 계산`(
        prize: LottoPrize,
        expectedPrize: Int,
    ) {
        assertThat(UserPrize(mapOf(prize to 1)).prizeCalculate()).isEqualTo(expectedPrize)
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
