package lotto.view

import lotto.model.Rank
import lotto.model.UserLottoTicket
import lotto.model.WinningTable

class OutputView {
    fun printLottoCount(
        manualLottoCount: Int,
        autoLottoCount: Int,
    ) {
        println(PURCHASE_MESSAGE.format(manualLottoCount, autoLottoCount))
    }

    fun printUserTickets(userTickets: List<UserLottoTicket>) {
        val userTicketsInt = userTickets.map { it.userLottoTicket.map { it.number } }
        userTicketsInt.forEach {
            println(it)
        }
    }

    fun printWinningChart(winningTable: WinningTable) {
        val rankList = Rank.entries.take(Rank.entries.size - 1).reversed()
        for (rank in rankList) {
            println(
                MATCH_MESSAGE.format(
                    rank.countOfMatch,
                    if (rank.matchBonus == true) ", 보너스 볼 일치" else "",
                    rank.winningMoney,
                    winningTable.winnings[rank]!!.num,
                ),
            )
        }
    }

    fun printWinningRate(winningRate: Float) {
        println(TOTAL_WINNING_RATE_MESSAGE.format(winningRate))
    }

    companion object {
        private const val PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val MATCH_MESSAGE = "%d개 일치%s (%d원) - %d개"
        private const val TOTAL_WINNING_RATE_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
