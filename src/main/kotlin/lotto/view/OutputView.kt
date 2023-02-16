package lotto.view

import lotto.domain.Lottery
import lotto.domain.NumberOfRank
import lotto.domain.Rank
import lotto.domain.Revenue

class OutputView {
    fun printLotteries(lotteries: List<Lottery>) {
        println("${lotteries.size}$NUMBER_OF_LOTTERY_GUIDE")
        lotteries.forEach { lottery ->
            println(lottery.numbers.sortedBy { it.number })
        }
    }

    fun printWinningResult(numberOfRank: NumberOfRank, profit: Double) {
        println(WINNING_RESULT_GUIDE)

        var rankAnnouncement = ""
        numberOfRank.numbers.forEach { (name, count) ->
            rankAnnouncement = getRankAnnouncement(name, count) + rankAnnouncement
        }
        print(rankAnnouncement)

        println("${TOTAL_PROFIT_GUIDE.format(profit)}${Revenue.valueOf(profit).description}")
    }

    fun printInterval() {
        println()
    }

    private fun getRankAnnouncement(name: String, count: Int): String {
        if (name == "MISS") return ""
        if (Rank.values().map { it.name }.contains(name)) {
            val rank = Rank.valueOf(name)
            return "${rank.description} (${rank.winningMoney}원) - ${count}개\n"
        }
        return ""
    }

    companion object {
        private const val NUMBER_OF_LOTTERY_GUIDE = "개를 구매했습니다."
        private const val WINNING_RESULT_GUIDE = "당첨 통계\n" +
            "---------"
        private const val TOTAL_PROFIT_GUIDE = "총 수익률은 %.2f입니다."
    }
}
