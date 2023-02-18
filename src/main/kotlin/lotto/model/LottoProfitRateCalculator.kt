package lotto.model

import lotto.entity.*

class LottoProfitRateCalculator : ProfitRateCalculator {
    override fun calculate(purchaseMoney: PurchaseMoney, winMoney: WinMoney): ProfitRate {
        return ProfitRate(winMoney.value / purchaseMoney.value.toFloat())
    }

}
