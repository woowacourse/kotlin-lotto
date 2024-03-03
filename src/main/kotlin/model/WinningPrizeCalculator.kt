package lotto.model

import lotto.util.InputValidation

object WinningPrizeCalculator {
    fun calculateProfitRate(
        purchaseAmount: Int,
        winningResult: Map<WinningRank, Int>,
    ): Double {
        InputValidation.validateAmountRange(purchaseAmount)
        val profitAmount = calculateProfitAmount(winningResult)
        return (profitAmount / purchaseAmount).toDouble()
    }

    private fun calculateProfitAmount(winningResult: Map<WinningRank, Int>): Int =
        winningResult.entries.sumOf {
            it.key.prize * (it.value)
        }.toInt()
}
