package view

import domain.Rank

class ResultView : ResultViewInterface {
    override fun printResult(statisticsResult: List<Int>, profit: String) {
        printStatistics(statisticsResult)
        printProfit(profit)
    }

    override fun printStatistics(statisticsResult: List<Int>) {
        println("당첨 통계")
        println("---------")
        for ((index, count) in statisticsResult.take(5).reversed().withIndex()) {
            val rank = Rank.values()[4 - index]
            println("${rank.countOfMatch}개 일치${printBonus(rank)}(${rank.winningMoney}원)- ${count}개")
        }
    }

    override fun printProfit(profit: String) {
        println("총 수익률은 ${profit}입니다.")
    }

    private fun printBonus(rank: Rank) = if (rank == Rank.SECOND) ", 보너스 볼 일치" else " "
}
