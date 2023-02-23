package lotto.view

import lotto.model.Rank
import lotto.model.UserLotto

object OutputView {
    private const val WINNING_STATISTICS = "\n당첨 통계\n---------"
    private const val MATCH_STANDARD = "%d개 일치"
    private const val MATCH_STANDARD_WITH_BONUS = ", 보너스볼 일치"
    private const val MATCH_MONEY = "(%d원)"
    private const val MATCH_COUNT = "- %d개"
    private const val EARNING_RATE = "총 수익률은 %s입니다."
    private const val LOSING_MONEY = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

    fun printMessage(msg: String) {
        println(msg)
    }

    fun printUserLotto(userLotto: UserLotto) {
        userLotto.lotto.forEach { lotto ->
            printMessage("[$lotto]")
        }
    }

    fun printResult(ranks: List<Int>, rates: String) {
        printMessage(WINNING_STATISTICS)

        val size = Rank.values().size
        Rank.values().reversed().subList(1, size).forEachIndexed { index, rank ->
            printEachRankResult(ranks.reversed().subList(1, size)[index], rank)
        }
        printEarningRate(rates)
    }

    private fun printEachRankResult(count: Int, rank: Rank) {
        var result = MATCH_STANDARD.format(rank.countOfMatch)
        if (rank.isContainBonus) {
            result += MATCH_STANDARD_WITH_BONUS
        }
        result += MATCH_MONEY.format(rank.winningMoney)
        result += MATCH_COUNT.format(count)

        printMessage(result)
    }

    private fun printEarningRate(rates: String) {
        printMessage(EARNING_RATE.format(rates))
        if (rates.toDouble() < 1)
            printMessage(LOSING_MONEY)
    }
}
