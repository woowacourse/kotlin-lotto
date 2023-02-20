package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ProfitCalculatorTest {
    @ParameterizedTest
    @MethodSource("calculateProfit")
    fun `수익률을 계산한다`(amount: Int, numbers: Map<String, Int>, expected: Double, testName: String) {
        println(testName)

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
                    14000,
                    mapOf(
                        "FIRST" to 0,
                        "SECOND" to 0,
                        "THIRD" to 0,
                        "FOURTH" to 0,
                        "FIFTH" to 1
                    ),
                    0.35,
                    "구입 금액이 14000원이고 5등이면 수익률은 0.35다"
                ),
                Arguments.of(
                    14000,
                    mapOf(
                        "FIRST" to 1,
                        "SECOND" to 0,
                        "THIRD" to 0,
                        "FOURTH" to 0,
                        "FIFTH" to 0
                    ),
                    142857.14,
                    "구입 금액이 14000원이고 1등이면 수익률은 142857.14다"
                ),
                Arguments.of(
                    1000,
                    mapOf(
                        "FIRST" to 0,
                        "SECOND" to 0,
                        "THIRD" to 0,
                        "FOURTH" to 0,
                        "FIFTH" to 1
                    ),
                    5.00,
                    "구입 금액이 1000원이고 5등이면 수익률은 5.00이다"
                )
            )
        }
    }
}
