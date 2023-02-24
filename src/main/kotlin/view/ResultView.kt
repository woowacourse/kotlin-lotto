package view

import domain.Lotto
import domain.Rank
import domain.Ticket

class ResultView : ResultViewInterface {
    override fun printResult(winningCountBy: Map<Rank, Int>, profit: String) {
        printStatistics(winningCountBy)
        printProfit(profit)
    }

    override fun printStatistics(winningCountBy: Map<Rank, Int>) {
        println("당첨 통계\n---------")
        Rank.values().reversed().forEach { rank ->
            val winningCount = winningCountBy[rank]!!
            printWinningCount(rank, winningCount)
        }
    }

    override fun printProfit(profit: String) {
        println("총 수익률은 ${profit}입니다.")
    }

    override fun printCount(count: Int) {
        println("${count}개를 구매했습니다.")
    }

    override fun printTicket(manualTicket: Ticket, autoTicket: Ticket) {
        println("수동으로 ${manualTicket.size}장, 자동으로 ${autoTicket.size}장을 구매했습니다.")
        manualTicket.forEach { lotto ->
            printLotto(lotto)
        }
        autoTicket.forEach { lotto ->
            printLotto(lotto)
        }
    }

    private fun printLotto(lotto: Lotto) {
        println(lotto.toList().sorted())
    }

    private fun isMatch(rank: Rank) = if (rank == Rank.SECOND) ", 보너스 볼 일치" else " "

    private fun printWinningCount(rank: Rank, winningCount: Int) {
        if (rank != Rank.MISS)
            println("${rank.countOfMatch}개 일치${isMatch(rank)}(${rank.winningMoney}원)- ${winningCount}개")
    }
}
