package lotto.view

import lotto.model.Rank
import lotto.model.UserLotto
import lotto.model.UserLottoCount

class OutputView : OutputInterface {
    override fun printPurchaseCounts(userLottoCount: UserLottoCount) {
        println(PURCHASE.format(userLottoCount.manual, userLottoCount.auto))
    }

    override fun printUserLottos(userLotto: UserLotto) {
        userLotto.lotto.forEach { lotto ->
            println(lotto)
        }
    }

    override fun printResult(ranks: List<Int>, rates: String) {
        println(WINNING_STATISTICS)
        println(DIVIDER)

        val size = Rank.values().size
        Rank.values().reversed().subList(1, size).forEachIndexed { index, rank ->
            printEachRankResult(ranks.reversed().subList(1, size)[index], rank)
        }

        print(EARNING_RATE.format(rates))
        if (rates.toDouble() < 1) {
            println(LOSING_MONEY)
        } else {
            println()
        }
    }

    private fun printEachRankResult(count: Int, rank: Rank) {
        var result = MATCH_STANDARD.format(rank.countOfMatch)
        if (rank == Rank.SECOND) {
            result += MATCH_STANDARD_WITH_BONUS
        }
        result += MATCH_MONEY.format(rank.winningMoney)
        result += MATCH_COUNT.format(count)

        println(result)
    }

    companion object {
        private const val PURCHASE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val WINNING_STATISTICS = "\n당첨 통계"
        private const val DIVIDER = "---------"
        private const val MATCH_STANDARD = "%d개 일치"
        private const val MATCH_STANDARD_WITH_BONUS = ", 보너스볼 일치"
        private const val MATCH_MONEY = "(%d원)"
        private const val MATCH_COUNT = "- %d개"
        private const val EARNING_RATE = "총 수익률은 %s입니다."
        private const val LOSING_MONEY = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    }
}
