package lotto.view

import lotto.model.Lotto
import lotto.model.LottoResult
import lotto.model.LottoStore
import lotto.model.RandomLottoGenerator
import lotto.model.Rank
import lotto.model.WinningLotto
import java.lang.String.format

class OutputView {
    fun printLottoCount(number: Int) {
        println(format(OUTPUT_LOTTO_COUNT_MESSAGE, number))
    }

    fun printLottoBundle(input: Int): List<Lotto> {
        val lottoBundle = LottoStore().getTickets(input, RandomLottoGenerator())
        lottoBundle.forEach { lotto ->
            println(lotto.numbers)
        }
        return lottoBundle
    }

    private fun isBonusMatch(rank: Rank) = if (rank == Rank.SECOND) OUTPUT_STATISTICS_BONUS_NUMBER_MESSAGE else " "

    fun printResult(
        winningLotto: WinningLotto,
        input: String,
        lottoBundle: List<Lotto>,
    ) {
        println(OUTPUT_STATISTIC_GUIDE_MESSAGE)
        val lottoResult = LottoResult()
        val winningCounts = LottoResult().getWinningCounts(lottoBundle, winningLotto)
        winningCounts.forEach { (rank, winningCount) ->
            if (rank != Rank.MISS) {
                println(
                    format(
                        OUTPUT_STATISTICS_MESSAGE,
                        rank.countOfMatch,
                        isBonusMatch(rank),
                        rank.winningMoney,
                        winningCount,
                    ),
                )
            }
        }
        println(format(OUTPUT_PROFIT_MESSAGE, lottoResult.calculateProfit(input, winningCounts)))
    }

    companion object {
        private const val OUTPUT_LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다."
        private const val OUTPUT_STATISTIC_GUIDE_MESSAGE = "\n당첨 통계\n---------"
        private const val OUTPUT_STATISTICS_MESSAGE = "%d개 일치%s(%d원)- %d개"
        private const val OUTPUT_STATISTICS_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치"
        private const val OUTPUT_PROFIT_MESSAGE = "총 수익률은 %s입니다."
    }
}
