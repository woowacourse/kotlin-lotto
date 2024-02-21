package lotto.model

object WinningPrizeCalculator {
    fun calculateProfitAmount(winningResult: Map<WinningRank, Int>) = winningResult.entries.sumOf {
        it.key.prize * (it.value)
    }

    fun calculateProfitRate(purchaseAmount:Int, profitAmount:Int):Double{
        return (profitAmount/purchaseAmount).toDouble()
    }
}
