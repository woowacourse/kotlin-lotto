package model.profit

import model.Money

class Profit {
    fun calculateRate(
        purchaseAmount: Money,
        totalWinningPrize: Money,
    ): ProfitRate = ProfitRate(totalWinningPrize / purchaseAmount)
}
