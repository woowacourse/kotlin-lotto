package lotto.model

import lotto.entity.Money
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney

interface ProfitRateCalculator {
    fun calculate(purchaseMoney: PurchaseMoney, winMoney: Money): ProfitRate
}
