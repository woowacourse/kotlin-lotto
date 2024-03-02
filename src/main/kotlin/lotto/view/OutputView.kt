package lotto.view

import lotto.model.Rank
import lotto.model.Ticket
import lotto.model.WinningResult

object OutputView {
    private const val LOTTERY_PURCHASED = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val LOTTERY_DELIMITER = ", "
    private const val HEADER_STATS =
        "\n" +
            "당첨 통계\n" +
            "-----------"
    private const val LOTTERY_ROI = "총 수익률은 %.2f 입니다"

    fun printTicketInfo(ticket: Ticket) {
        val userLotteries = ticket.userLotteries

        println(LOTTERY_PURCHASED.format(userLotteries.count { it.isManual }, userLotteries.count { !it.isManual }))
        ticket.userLotteries.forEach { lottery ->
            print("[${lottery.toString(LOTTERY_DELIMITER)}]\n")
        }
        println()
    }

    fun printWinningResult(winningResult: WinningResult) {
        println(HEADER_STATS)
        Rank.entries.filter { it != Rank.MISS }.forEach {
            println("${it.getMessage()} - ${winningResult.stats[it] ?: 0}개")
        }
        println(LOTTERY_ROI.format(winningResult.roi))
    }

    fun printErrorMessage(t: Throwable) {
        println(t.message)
    }
}
