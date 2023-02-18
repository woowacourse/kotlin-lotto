package view

import domain.Rank

class ResultView : ResultViewInterface {
    override fun printResult(statisticsResult: Map<Rank, Int>, profit: String) {
        printStatistics(statisticsResult)
        printProfit(profit)
    }

    override fun printStatistics(statisticsResult: Map<Rank, Int>) {
        println("당첨 통계\n---------")
        Rank.values().reversed().forEach { rank ->
            val winningCount = statisticsResult[rank]!!
            printWinningCount(rank, winningCount)
        }
    }

    override fun printProfit(profit: String) {
        println("총 수익률은 ${profit}입니다.")
    }

    private fun printBonus(rank: Rank) = if (rank == Rank.SECOND) ", 보너스 볼 일치" else " "

    private fun printWinningCount(rank: Rank, winningCount: Int) {
        if (rank != Rank.MISS)
            println("${rank.countOfMatch}개 일치${printBonus(rank)}(${rank.winningMoney}원)- ${winningCount}개")
    }
}
