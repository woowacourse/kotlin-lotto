package lotto.model

object WinningPrizeCalculator {
    fun calculateProfitAmount(winningResult: Map<WinningRank, Int>): Int =
        winningResult.entries.sumOf {
            it.key.prize * (it.value)
        }.toInt()

    fun calculateProfitRate(
        purchaseAmount: Int,
        profitAmount: Int,
    ): Double {
        return (profitAmount / purchaseAmount).toDouble()
    }
}
