package lotto.view

import lotto.constants.LottoPrize
import lotto.constants.StringConstants
import lotto.constants.StringConstants.OUTPUT_PROFIT_RATIO
import lotto.model.LottoStore
import lotto.model.ProfitRatio
import lotto.model.WinningStatistics

object OutputView {
    fun printPurchaseLotto(lottoStore: LottoStore) {
        println(StringConstants.OUTPUT_PURCHASE_COUNT.format(lottoStore.lottos.size))
        lottoStore.lottos.forEach { println(it) }
        println()
    }

    fun printWinningStatistics(winningStatistics: WinningStatistics) {
        println(StringConstants.OUTPUT_WINNING_STATICS)
        println(StringConstants.OUTPUT_DIVIDER)

        LottoPrize.entries.forEach { lottoPrize ->
            if (lottoPrize == LottoPrize.NOTHING) return@forEach
            val matchingCount = winningStatistics[lottoPrize]
            println(provideMatchingMessage(lottoPrize, matchingCount))
        }
    }

    private fun provideMatchingMessage(
        lottoPrize: LottoPrize,
        matchingCount: Int,
    ): String {
        if (lottoPrize.isBonus()) {
            return StringConstants.OUTPUT_MATCHING_COUNT_BONUS.format(
                lottoPrize.matchingCount,
                lottoPrize.amount,
                matchingCount,
            )
        }

        return StringConstants.OUTPUT_MATCHING_COUNT.format(
            lottoPrize.matchingCount,
            lottoPrize.amount,
            matchingCount,
        )
    }

    fun printProfitRatio(profitRatio: ProfitRatio) = println(OUTPUT_PROFIT_RATIO.format(profitRatio))
}
