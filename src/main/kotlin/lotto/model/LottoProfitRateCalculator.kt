package lotto.model

import lotto.entity.InputMoney
import lotto.entity.ProfitRate
import lotto.entity.WinStatistics

class LottoProfitRateCalculator(private val inputMoney: InputMoney, private val winStatistics: WinStatistics) : ProfitRateCalculator {
    override fun calculate(): ProfitRate {
        val winMoney = winStatistics.value.sumOf { it.winningMoney }
        return ProfitRate(winMoney / inputMoney.value.toFloat())
    }
}
