package lotto.model

import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinStatistics

class LottoProfitRateCalculator(private val purchaseMoney: PurchaseMoney, private val winStatistics: WinStatistics) : ProfitRateCalculator {
    override fun calculate(): ProfitRate {
        val winMoney = winStatistics.value.sumOf { it.winningMoney }
        return ProfitRate(winMoney / purchaseMoney.value.toFloat())
    }
}
