package lotto

import lotto.constants.LottoPrize
import lotto.model.PurchaseInfo
import lotto.model.WinningStatistics
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningStatisticsTest {
    @ParameterizedTest
    @MethodSource("로또 수익률 계산 테스트 데이터")
    fun `로또 수익률을 계산한다`(
        lottoPrice: Int,
        statistics: Map<LottoPrize, Int>,
        expected: String,
    ) {
        // given
        val purchaseInfo = PurchaseInfo(lottoPrice)
        val winningStatistics = WinningStatistics(statistics)

        // when
        val actual = winningStatistics.calculateProfitRatio(purchaseInfo)

        // then
        Assertions.assertThat(actual.toString()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `로또 수익률 계산 테스트 데이터`() =
            listOf(
                Arguments.of("14000", mapOf(LottoPrize.FIFTH to 1), "0.35"),
                Arguments.of("8000", mapOf(LottoPrize.FIFTH to 1), "0.62"),
                Arguments.of("1000", mapOf(LottoPrize.FIRST to 1), "2000000.00"),
            )
    }
}
