package view

import entity.Ticket
import model.Rank
import model.WinningResult

object OutputView {
    private const val LOTTO_PURCHASED = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val HEADER_STATS =
        "\n" +
            "당첨 통계\n" +
            "-----------"

    fun printTicketInfo(ticket: Ticket) {
        println(
            LOTTO_PURCHASED.format(
                ticket.lotteryPurchasePattern.manualLottoCount,
                ticket.lotteryPurchasePattern.autoLottoCount,
            ),
        )
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
