package lotto.model

class LottoProfitCalculator {
    fun getProfitRate(
        winningResult: Map<Rank, Int>,
        purchaseAmount: Amount,
    ): Float {
        val totalProfit = winningResult.entries.sumOf { rank -> rank.key.winningMoney * rank.value }
        return formatProfitRate(totalProfit, purchaseAmount)
    }

    private fun formatProfitRate(
        totalProfit: Int,
        purchaseAmount: Amount,
    ): Float = totalProfit.toFloat() / purchaseAmount.value.toFloat()
}
