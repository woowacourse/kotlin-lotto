package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ProfitCalculatorTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("calculateProfit")
    fun `수익률을 계산한다`(testName: String, amount: Int, numbers: Map<Rank, Int>, expected: Double) {
        val money = PurchaseAmount(amount)
        val counter = RankCounter(numbers)

        val actual = ProfitCalculator.calculate(money, counter.calculateTotalPrize())

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun calculateProfit(): Array<Arguments> {
            return arrayOf(
                Arguments.of(
                    "구입 금액이 14000원이고 5등이면 수익률은 0.35다",
                    14000,
                    mapOf(
                        Rank.FIRST to 0,
                        Rank.SECOND to 0,
                        Rank.THIRD to 0,
                        Rank.FOURTH to 0,
                        Rank.FIFTH to 1,
                        Rank.MISS to 5
                    ),
                    0.35
                ),
                Arguments.of(
                    "구입 금액이 14000원이고 1등이면 수익률은 142857.14다",
                    14000,
                    mapOf(
                        Rank.FIRST to 1,
                        Rank.SECOND to 0,
                        Rank.THIRD to 0,
                        Rank.FOURTH to 0,
                        Rank.FIFTH to 0,
                        Rank.MISS to 4
                    ),
                    142857.14
                ),
                Arguments.of(
                    "구입 금액이 1000원이고 5등이면 수익률은 5.00이다",
                    1000,
                    mapOf(
                        Rank.FIRST to 0,
                        Rank.SECOND to 0,
                        Rank.THIRD to 0,
                        Rank.FOURTH to 0,
                        Rank.FIFTH to 1,
                        Rank.MISS to 2
                    ),
                    5.00
                )
            )
        }
    }
}
