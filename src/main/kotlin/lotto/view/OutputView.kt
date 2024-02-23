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

    fun printWinningStatistics(
        prizeCount: Map<LottoPrize, Int>,
        profitRatio: Double,
    ) {
        println(StringConstants.OUTPUT_WINNING_STATICS)
        println(StringConstants.OUTPUT_DIVIDER)

        LottoPrize.entries.forEach { lottoPrize ->
            if (lottoPrize == LottoPrize.NOTHING) return@forEach
            val matchingCount = prizeCount.getOrDefault(lottoPrize, DEFAULT_MATCHING_COUNT)
            println(provideMatchingMessage(lottoPrize, matchingCount))
        }
        println(OUTPUT_PROFIT_RATIO.format(profitRatio.provideTwoDecimal()))
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

    private fun Double.provideTwoDecimal() = "%.2f".format(this)
}
