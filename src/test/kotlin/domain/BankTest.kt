package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankTest {
    @Test
    fun `당첨 통계를 계산한다`() {
        // given
        val winningResult: WinningResult = WinningResult(
            listOf(
                ComparingResultDto(6, false),
                ComparingResultDto(5, true),
                ComparingResultDto(5, false),
                ComparingResultDto(5, false),
            ),
        )

        val expected: WinStatistics = WinStatistics(
            mutableMapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 2,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
            ),
        )

        // when
        val actual: WinStatistics = Bank.getWinStatistics(winningResult)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수익률을 계산하여 반환한다`() {
        // given
        val totalPrize: Money = Money(10000)
        val spendMoney: Money = Money(1000)

        // when
        val actual: Double = Bank.getEarningRate(totalPrize, spendMoney)

        // then
        assertThat(actual).isEqualTo(10.0)
    }
}
