package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinStatisticsTest {
    @Test
    fun `당첨 통계와 사용한 돈에 따라 수익률을 계산한다`() {
        // given
        val winStatistics: WinStatistics = WinStatistics(
            listOf(
                ComparingResultDto(6, false),
                ComparingResultDto(6, false),
                ComparingResultDto(5, true),
                ComparingResultDto(5, false),
                ComparingResultDto(5, false),
            ),
        ) // 당첨 상금: 4033000000
        val spentMoney: Money = Money(5000)
        val expected: Double = 806600.0

        // when
        val actual = winStatistics.calculateEarningRate(spentMoney)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
