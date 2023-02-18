package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinStatisticsTest {
    @Test
    fun `당첨통계를 통해 총 수익이 얼마인지 반환한다`() {
        // given
        val winStatistics: WinStatistics = WinStatistics(
            mutableMapOf(
                Rank.FIRST to 2,
                Rank.SECOND to 1,
                Rank.THIRD to 2,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
            )
        )

        val expected: Money = Money.create(4033000000)

        // when
        val actual = winStatistics.getTotalIncome()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
