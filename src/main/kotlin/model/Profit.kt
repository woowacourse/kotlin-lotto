package model

class Profit {
    fun calculateRate(
        purchaseAmount: Money,
        totalWinningPrize: Money,
    ): ProfitRate = ProfitRate(totalWinningPrize / purchaseAmount)
}
