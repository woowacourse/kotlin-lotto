package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinStatisticsTest {
    @Test
    fun `당첨통계를 통해 총 수익이 얼마인지 반환한다`() {
        // given
        val winStatistics: WinStatistics = WinStatistics(
            WinningResult(
                listOf(
                    ComparingResult(6, false),
                    ComparingResult(6, false),
                    ComparingResult(5, true),
                    ComparingResult(5, false),
                    ComparingResult(5, false),
                )
            )
        )

        val expected: Long = 4033000000

        // when
        val actual = winStatistics.getTotalIncome()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `당첨 통계를 계산한다`() {
        // given
        val winningResult: WinningResult = WinningResult(
            listOf(
                ComparingResult(6, false),
                ComparingResult(5, true),
                ComparingResult(5, false),
                ComparingResult(5, false),
            )
        )
        val expected: Map<Rank, Int> = mapOf(
            Rank.FIRST to 1,
            Rank.SECOND to 1,
            Rank.THIRD to 2,
            Rank.FOURTH to 0,
            Rank.FIFTH to 0,
            Rank.MISS to 0
        )

        // when
        val actual: WinStatistics = WinStatistics(winningResult)

        // then
        assertThat(actual.rankCount).isEqualTo(expected)
    }
}
