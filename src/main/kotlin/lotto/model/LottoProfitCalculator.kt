package lotto.model

class LottoProfitCalculator {
    fun getProfitRate(
        countLottoByRank: Map<Rank, Int>,
        purchaseAmount: Int,
    ): Float {
        val totalProfit = countLottoByRank.entries.sumOf { it.key.winningMoney * it.value }
        return formatProfitRate(totalProfit, purchaseAmount)
    }

    private fun formatProfitRate(
        totalProfit: Int,
        purchaseAmount: Int,
    ): Float = totalProfit.toFloat() / purchaseAmount.toFloat()
}
