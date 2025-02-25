package lotto.view

import lotto.model.Lotto
import lotto.model.LottoStore
import lotto.model.Rank
import lotto.model.generator.ManualLottoGenerator
import lotto.model.generator.RandomLottoGenerator
import java.lang.String.format

class LottoOutputView {
    fun printLottoCount(numbers: List<Int>) {
        println(format(OUTPUT_LOTTO_COUNT_MESSAGE, numbers[0], numbers[1]))
    }

    fun printLottoBundle(input: Int): List<Lotto> {
        val lottoBundle = LottoStore().getTickets(input, RandomLottoGenerator())
        lottoBundle.forEach { lotto ->
            println(lotto.numbers.map { it.number })
        }
        return lottoBundle
    }

    fun printManualLottoBundle(
        input: Int,
        lottoBundle: List<List<Int>>,
    ): List<Lotto> {
        val manualLottoBundle = LottoStore().getTickets(input, ManualLottoGenerator(lottoBundle))
        manualLottoBundle.forEach { lotto ->
            println(lotto.numbers.map { it.number })
        }
        return manualLottoBundle
    }

    private fun isBonusMatch(rank: Rank) = if (rank == Rank.SECOND) OUTPUT_STATISTICS_BONUS_NUMBER_MESSAGE else " "

    fun printStatistics(winningStatistics: Map<Rank, Int>) {
        println(OUTPUT_STATISTIC_GUIDE_MESSAGE)
        winningStatistics.forEach { (rank, count) ->
            if (rank != Rank.MISS) {
                println(
                    format(
                        OUTPUT_STATISTICS_MESSAGE,
                        rank.countOfMatch,
                        isBonusMatch(rank),
                        rank.winningMoney,
                        count,
                    ),
                )
            }
        }
    }

    fun printProfit(profit: String) {
        println(format(OUTPUT_PROFIT_MESSAGE, profit))
    }

    companion object {
        private const val OUTPUT_LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val OUTPUT_STATISTIC_GUIDE_MESSAGE = "\n당첨 통계\n---------"
        private const val OUTPUT_PROFIT_MESSAGE = "총 수익률은 %s입니다."
        private const val OUTPUT_STATISTICS_MESSAGE = "%d개 일치%s(%d원)- %d개"
        private const val OUTPUT_STATISTICS_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치"
    }
}
