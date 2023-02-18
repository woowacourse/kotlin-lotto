package lotto.model

import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinStatistics

class LottoProfitRateCalculator : ProfitRateCalculator {
    override fun calculate(purchaseMoney: Int, winMoney: Int): ProfitRate {
        return ProfitRate(winMoney / purchaseMoney.toFloat())
    }

    fun calculateWinMoney(winStatistics: WinStatistics): Int = winStatistics.value.sumOf { it.winningMoney }
}
