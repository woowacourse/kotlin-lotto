package lotto.view

import lotto.model.Lotto
import lotto.model.LottoPrize
import lotto.model.ProfitRatio
import lotto.model.WinningStatistics

object OutputView {
    private const val OUTPUT_PURCHASE_COUNT = "%d개를 구매했습니다."
    private const val OUTPUT_MATCHING_COUNT = "%d개 일치 (%d원)- %d개"
    private const val OUTPUT_MATCHING_COUNT_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개"
    private const val OUTPUT_WINNING_STATICS = "당첨 통계"
    private const val OUTPUT_DIVIDER = "---------"
    private const val OUTPUT_PROFIT_RATIO = "총 수익률은 %s입니다."

    fun printPurchaseLotto(lottos: List<Lotto>) {
        println(OUTPUT_PURCHASE_COUNT.format(lottos.size))
        lottos.forEach { println(it.numbers.toString()) }
        println()
    }

    fun printWinningStatistics(winningStatistics: WinningStatistics) {
        println(OUTPUT_WINNING_STATICS)
        println(OUTPUT_DIVIDER)

        LottoPrize.entries.forEach { lottoPrize ->
            if (lottoPrize == LottoPrize.NOTHING) return@forEach
            val winningCount = winningStatistics[lottoPrize]
            println(getWinningCountMessage(lottoPrize, winningCount))
        }
    }

    private fun getWinningCountMessage(
        lottoPrize: LottoPrize,
        winningCount: Int,
    ): String {
        if (lottoPrize.isBonus()) {
            return OUTPUT_MATCHING_COUNT_BONUS.format(
                lottoPrize.matchingCount,
                lottoPrize.amount,
                winningCount,
            )
        }

        return OUTPUT_MATCHING_COUNT.format(
            lottoPrize.matchingCount,
            lottoPrize.amount,
            winningCount,
        )
    }

    fun printProfitRatio(profitRatio: ProfitRatio) {
        val outputProfitRatio = "%.2f".format(profitRatio.ratio)
        println(OUTPUT_PROFIT_RATIO.format(outputProfitRatio))
    }
}
