package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningResultCalculatorTest {
    @ParameterizedTest
    @MethodSource("calculateProfit")
    fun `수익률을 계산한다`(amount: Int, numbers: Map<String, Int>, expected: Double) {
        val result = WinningResultCalculator()
        val money = PurchaseAmount(amount)
        val counter = NumberOfRank(numbers)

        val actual = result.calculateProfit(money, counter.calculateTotalPrize())

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
                    0.35
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
                    142857.14
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
                    5
                )
            )
        }
    }
}
