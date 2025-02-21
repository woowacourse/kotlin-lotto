package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoProfitCalculatorTest {
    @Test
    fun `로또의 당첨 결과에 맞는 수익률을 소수점 둘째 자리까지 반환한다`() {
        val winningResult =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 0,
                Rank.FIFTH to 1,
                Rank.MISS to 100,
            )

        val totalAmount = winningResult.values.size * 1000
        val profitCalculator = LottoProfitCalculator()

        val totalProfit = (Rank.FIRST.winningMoney + Rank.FIFTH.winningMoney).toFloat()
        val expectedProfitRate = (totalProfit / totalAmount.toFloat())

        assertEquals(expectedProfitRate, profitCalculator.getProfitRate(winningResult, totalAmount))
    }
}
