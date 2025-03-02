package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Rank
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningStatisticsTest {
    @Test
    fun `당첨 통계를 계산한다`() {
        // given
        val lottoBundle = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)), Lotto(listOf(3, 4, 5, 6, 7, 8)))
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoNumber.from(7))

        // when
        val result = WinningStatistics.calculateStatistics(lottoBundle, winningLotto)

        // then
        val expectedStatistics =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 0,
                Rank.MISS to 0,
            )
        assertThat(result).isEqualTo(expectedStatistics)
    }
}
