package lotto.model

class LottoProfitRateCalculator(val inputMoney: Int, val winStat: List<Rank>) : ProfitRateCalculator {
    override fun calculate(): ProfitRate {
        val winMoney = winStat.sumOf { it.winningMoney }
        return ProfitRate(winMoney / inputMoney.toFloat())
    }
}
