package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ProfitCalculatorTest {
    @ParameterizedTest(name = "구입 금액이 {0}원이고 1등 {1}명, 2등 {2}명, 3등 {3}명, 4등 {4}명, 5등 {5}명이면 수익률은 {7}이다")
    @CsvSource(
        "14000, 0, 0, 0, 0, 1, 5, 0.35",
        "14000, 1, 0, 0, 0, 0, 4, 142857.14",
        "1000, 0, 0, 0, 0, 1, 2, 5.00"
    )
    fun `수익률을 계산한다`(
        amount: Int,
        first: Int,
        second: Int,
        third: Int,
        fourth: Int,
        fifth: Int,
        miss: Int,
        expected: Double
    ) {
        val money = PurchaseAmount(amount)
        val numbers = mapOf(
            Rank.FIRST to first,
            Rank.SECOND to second,
            Rank.THIRD to third,
            Rank.FOURTH to fourth,
            Rank.FIFTH to fifth,
            Rank.MISS to miss
        )
        val counter = RankCounter(numbers)

        val actual = ProfitCalculator.calculate(money, counter.calculateTotalPrize())

        assertThat(actual).isEqualTo(expected)
    }
}
