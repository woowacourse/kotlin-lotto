package lotto.model

class LottoProfitCalculator {
    fun getProfitRate(
        winningResult: Map<Rank, Int>,
        purchaseAmount: Int,
    ): Float {
        val totalProfit = winningResult.entries.sumOf { rank -> rank.key.winningMoney * rank.value }
        return formatProfitRate(totalProfit, purchaseAmount)
    }

    private fun formatProfitRate(
        totalProfit: Int,
        purchaseAmount: Int,
    ): Float = totalProfit.toFloat() / purchaseAmount.toFloat()
}
