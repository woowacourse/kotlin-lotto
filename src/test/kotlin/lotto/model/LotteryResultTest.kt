package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteryResultTest {
    @Test
    fun `내가 구매한 로또와 당첨 결과를 비교하여 당첨 현황을 반환한다`() {
        val winningNumbers =
            Lotto(setOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)))
        val bonusNumber = LottoNumber(7)
        val mine =
            listOf(
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
                Lotto(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(7),
                    ),
                ),
            )
        val expected = mapOf(WinningRank.FIRST to 1, WinningRank.SECOND to 1)
        val actual = LotteryResultAnalyzer(winningNumbers, bonusNumber).generateWinningStatus(mine)
        assertThat(actual).isEqualTo(expected)
    }
}
