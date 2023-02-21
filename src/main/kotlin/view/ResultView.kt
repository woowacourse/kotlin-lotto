package view

import domain.LottoPurchaseInfo
import domain.Rank
import domain.Ticket

class ResultView : ResultViewInterface {
    override fun printResult(statisticsResult: Map<Rank, Int>, profit: String) {
        printStatistics(statisticsResult)
        printProfit(profit)
    }

    override fun printTicket(purchaseInfo: LottoPurchaseInfo, ticket: Ticket) {
        println(PURCHASE_MANUAL_AND_AUTO_TICKET_COUNT.format(purchaseInfo.manualCount, purchaseInfo.autoCount))
        ticket.lottos.forEach { lotto ->
            println(lotto.toString())
        }
        println()
    }

    override fun printStatistics(statisticsResult: Map<Rank, Int>) {
        println(MATCH_RESULT)
        for (rank in Rank.values().take(5).reversed()) {
            val rankCount = statisticsResult[rank]
            println(PER_MATCH_RESULT.format(rank.countOfMatch, printBonus(rank), rank.winningMoney, rankCount))
        }
    }

    override fun printProfit(profit: String) {
        println(PROFIT_RESULT.format(profit))
    }

    private fun printBonus(rank: Rank) = if (rank == Rank.SECOND) BONUS_MATCH else NO_BONUS_MATCH

    companion object {
        private const val PURCHASE_MANUAL_AND_AUTO_TICKET_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val MATCH_RESULT = "당첨 통계\n" + "---------"
        private const val PROFIT_RESULT = "총 수익률은 %s입니다."
        private const val PER_MATCH_RESULT = "%d개 일치%s(%d원)- %d개"
        private const val BONUS_MATCH = ", 보너스 볼 일치"
        private const val NO_BONUS_MATCH = " "
    }
}
