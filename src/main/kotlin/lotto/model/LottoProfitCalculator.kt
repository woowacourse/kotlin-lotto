package lotto.model

class LottoProfitCalculator {
    fun getProfitResult(
        winningResult: Map<Rank, Int>,
        purchaseAmount: Amount,
    ): ProfitResult {
        val totalProfit = winningResult.entries.sumOf { rank -> rank.key.winningMoney * rank.value }
        val formattedProfit = formatProfitRate(totalProfit, purchaseAmount)

        return ProfitResult(formattedProfit)
    }

    private fun formatProfitRate(
        totalProfit: Int,
        purchaseAmount: Amount,
    ): Float = totalProfit.toFloat() / purchaseAmount.value.toFloat()
}
