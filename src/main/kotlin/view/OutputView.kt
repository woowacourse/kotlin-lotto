package view

import entity.Ticket
import model.Rank
import model.WinningResult

object OutputView {
    private const val LOTTO_PURCHASED = "%d개를 구매했습니다."
    private const val HEADER_STATS =
        "\n" +
            "당첨 통계\n" +
            "-----------"

    fun printTicketInfo(ticket: Ticket) {
        println(LOTTO_PURCHASED.format(ticket.userLotteries.size))
        ticket.userLotteries.forEach {
            print(it.toString())
        }
        println()
    }

    fun printWinningResult(winningResult: WinningResult) {
        println(HEADER_STATS)
        Rank.entries.filter { it != Rank.MISS }.forEach {
            println("${it.getMessage()} - ${winningResult.stats[it] ?: 0}개")
        }
        println("총 수익률은 ${winningResult.roi} 입니다.")
    }
}
