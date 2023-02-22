package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoBunchTest {
    val lottoBunch =
        lottoBunchOf(lottoOf(1, 2, 3, 4, 5, 6), lottoOf(7, 8, 9, 10, 11, 12), lottoOf(13, 14, 15, 16, 17, 18))

    val winningLotto: WinningLotto =
        winningLottoOf(mainLottoNumbers = intArrayOf(1, 10, 3, 4, 5, 6), bonusLottoNumber = 2)

    @Test
    fun `2등에 1번 당첨되었다`() {
        assertThat(
            lottoBunch.calcRanks(winningLotto),
        ).containsOnlyOnce(Rank.SECOND)
    }

    @Test
    fun `2등에 1번 당첨된경우 최종 당첨금액은 30000000다`() {
        assertThat(lottoBunch.sumTotalPrizeMoney(WinningResult.from(lottoBunch.calcRanks(winningLotto)))).isEqualTo(
            30000000,
        )
    }

    companion object {
        private fun lottoBunchOf(vararg lotto: Lotto) = LottoBunch(lotto.toList())

        private fun lottoOf(vararg numbers: Int): Lotto =
            Lotto(numbers.map { LottoNumber(it) }.toSet())

        private fun winningLottoOf(vararg mainLottoNumbers: Int, bonusLottoNumber: Int): WinningLotto =
            WinningLotto(Lotto(mainLottoNumbers.map { LottoNumber(it) }.toSet()), LottoNumber(bonusLottoNumber))
    }
}
