package lotto.model

import lotto.entity.ProfitRate

interface ProfitRateCalculator {
    fun calculate(purchaseMoney: Int, winMoney: Int): ProfitRate
}
