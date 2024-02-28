package lotto.model

data class WinningStatus(val resultStatus: Map<WinningRank, Int>) {
    fun getEarningRate(purchaseAmount: Int): Double {
        val totalProfit: Long = resultStatus.entries.sumOf { it.key.winningMoney * it.value }
        return (totalProfit / purchaseAmount.toDouble())
    }
}
