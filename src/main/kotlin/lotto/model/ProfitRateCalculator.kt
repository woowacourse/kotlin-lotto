package lotto.model

import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinMoney

interface ProfitRateCalculator {
    fun calculate(purchaseMoney: PurchaseMoney, winMoney: WinMoney): ProfitRate
}
