package lotto.view

import lotto.domain.Lottery
import lotto.domain.Rank
import lotto.domain.Receipt

class OutputView {
    fun printLotteryTickets(receipt: Receipt, tickets: List<Lottery>) {
        println("${NUMBER_OF_LOTTERY_TICKETS_GUIDE.format(receipt.manual.count, receipt.auto.count)}")
        tickets.forEach { ticket ->
            println(ticket.numbers.map { it.toInt() }.sorted())
        }
    }

    fun printWinningResult(countResult: Map<Rank, Int>, profit: Double) {
        println(WINNING_RESULT_GUIDE)

        var rankAnnouncement = StringBuilder()
        countResult.forEach { (rank, count) ->
            rankAnnouncement.insert(0, getRankAnnouncement(rank, count))
        }
        print(rankAnnouncement)

        println("${TOTAL_PROFIT_GUIDE.format(profit)}${Revenue.valueOf(profit).description}")
    }

    private fun getRankAnnouncement(rank: Rank, count: Int): String {
        if (rank == Rank.MISS) return ""

        val announcement = StringBuilder()
        announcement.append(COUNT_OF_MATCH_DESCRIPTION.format(rank.countOfMatch))
        if (rank == Rank.SECOND) {
            announcement.append(BONUS_MATCH_DESCRIPTION)
        }
        announcement.append(PRIZE_AND_COUNT_RESULT_DESCRIPTION.format(rank.winningMoney, count))
        return announcement.toString()
    }

    enum class Revenue(val description: String) {
        LOSS("(기준이 1이기 때문에 결과적으로 손해라는 의미임)"),
        NOTHING("(기준이 1이기 때문에 결과적으로 손해도 이득도 아니라는 의미임)"),
        GAIN("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");

        companion object {
            private const val STANDARD_VALUE = 1

            fun valueOf(profit: Double): Revenue {
                if (profit < STANDARD_VALUE) return LOSS
                if (profit > STANDARD_VALUE) return GAIN
                return NOTHING
            }
        }
    }

    fun printInterval() {
        println()
    }

    companion object {
        private const val BONUS_MATCH_DESCRIPTION = ", 보너스 볼 일치"
        private const val COUNT_OF_MATCH_DESCRIPTION = "%d개 일치"
        private const val NUMBER_OF_LOTTERY_TICKETS_GUIDE = "수동으로 %d장, 자동으로 %d장 구매했습니다."
        private const val PRIZE_AND_COUNT_RESULT_DESCRIPTION = " (%d원) - %d개\n"
        private const val TOTAL_PROFIT_GUIDE = "총 수익률은 %.2f입니다."
        private const val WINNING_RESULT_GUIDE = "당첨 통계\n" +
            "---------"
    }
}
