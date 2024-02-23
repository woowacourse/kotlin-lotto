package lotto.view

import lotto.constants.LottoPrize
import lotto.constants.StringConstants
import lotto.constants.StringConstants.OUTPUT_PROFIT_RATIO
import lotto.model.LottoStore

object OutputView {
    private const val DEFAULT_MATCHING_COUNT = 0

    fun printPurchaseLotto(lottoStore: LottoStore) {
        println(StringConstants.OUTPUT_PURCHASE_COUNT.format(lottoStore.lottos.size))
        lottoStore.lottos.forEach { println(it) }
        println()
    }

    fun printWinningStatistics(winningStatistics: Map<LottoPrize, Int>) {
        println(StringConstants.OUTPUT_WINNING_STATICS)
        println(StringConstants.OUTPUT_DIVIDER)

        LottoPrize.entries.forEach { lottoPrize ->
            if (lottoPrize == LottoPrize.NOTHING) return@forEach
            val matchingCount = winningStatistics.getOrDefault(lottoPrize, DEFAULT_MATCHING_COUNT)
            println(provideMatchingMessage(lottoPrize, matchingCount))
        }
    }

    private fun provideMatchingMessage(
        lottoPrize: LottoPrize,
        matchingCount: Int,
    ): String {
        if (lottoPrize == LottoPrize.SECOND) {
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

    fun printProfitRatio(profitRatio: Double) = println(OUTPUT_PROFIT_RATIO.format(profitRatio.convertTwoDecimal()))

    private fun Double.convertTwoDecimal() = "%.2f".format(this)
}
