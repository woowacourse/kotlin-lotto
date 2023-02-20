package lotto.model

import lotto.entity.Money
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney

class LottoProfitRateCalculator : ProfitRateCalculator {
    override fun calculate(purchaseMoney: PurchaseMoney, winMoney: Money): ProfitRate {
        return ProfitRate(winMoney.value / purchaseMoney.value.toFloat())
    }
}
