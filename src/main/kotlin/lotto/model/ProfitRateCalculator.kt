package lotto.model

import lotto.entity.ProfitRate

interface ProfitRateCalculator {
    fun calculate(): ProfitRate
}
