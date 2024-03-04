package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningStatisticsTest {
    @ParameterizedTest
    @MethodSource("로또 수익률 계산 테스트 데이터")
    fun `로또 수익률을 계산한다`(
        purchasePrice: Int,
        statistics: Map<LottoPrize, Int>,
        expected: Double,
    ) {
        // given
        val winningStatistics = WinningStatistics(statistics)

        // when
        val actual = winningStatistics.calculateProfitRatio(purchasePrice).ratio

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("로또 순위 개수 테스트 데이터")
    fun `로또 순위가 몇번 당첨되었는지 알려준다`(
        lottoPrize: LottoPrize,
        expected: Int,
    ) {
        // given
        val winningStatistics =
            WinningStatistics(
                mapOf(
                    LottoPrize.FIFTH to 1,
                    LottoPrize.FOURTH to 2,
                    LottoPrize.THIRD to 3,
                    LottoPrize.SECOND to 4,
                    LottoPrize.FIRST to 5,
                ),
            )

        // when
        val actual = winningStatistics.getPrizeCount(lottoPrize)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `로또 수익률 계산 테스트 데이터`() =
            listOf(
                Arguments.of("14000", mapOf(LottoPrize.FIFTH to 1), 0.35),
                Arguments.of("8000", mapOf(LottoPrize.FIFTH to 1), 0.62),
                Arguments.of("1000", mapOf(LottoPrize.FIRST to 1), 2000000.00),
            )

        @JvmStatic
        fun `로또 순위 개수 테스트 데이터`() =
            listOf(
                Arguments.of(LottoPrize.FIFTH, 1),
                Arguments.of(LottoPrize.FOURTH, 2),
                Arguments.of(LottoPrize.THIRD, 3),
                Arguments.of(LottoPrize.SECOND, 4),
                Arguments.of(LottoPrize.FIRST, 5),
            )
    }
}
