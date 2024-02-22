package lotto.view

import lotto.constants.LottoPrize
import lotto.constants.StringConstants
import lotto.constants.StringConstants.OUTPUT_PROFIT_RATIO
import lotto.model.LottoStore

object OutputView {

    private const val DEFAULT_MATCHING_COUNT = 0

    fun printPurchaseLotto(lottoStore: LottoStore) {
        println(StringConstants.OUTPUT_PURCHASE_COUNT.format(lottoStore.lottos.size))
        lottoStore.lottos.forEach { lotto ->
            println(lotto)
        }
        println()
    }

    fun printWinningStatistics(prizeCount: Map<LottoPrize, Int>, profitRatio: Double) {
        println(StringConstants.OUTPUT_WINNING_STATICS)
        println(StringConstants.OUTPUT_DIVIDER)
        LottoPrize.values().forEach { lottoPrize ->
            val matchingCount = prizeCount.getOrDefault(lottoPrize, DEFAULT_MATCHING_COUNT)
            println(provideMatchingMessage(lottoPrize, matchingCount))
        }
        println(OUTPUT_PROFIT_RATIO.format(profitRatio))
    }

    private fun provideMatchingMessage(lottoPrize: LottoPrize, matchingCount: Int) =
        if (lottoPrize == LottoPrize.SECOND) {
            StringConstants.OUTPUT_MATCHING_COUNT_BONUS.format(
                lottoPrize.matchingCount,
                lottoPrize.amount,
                matchingCount
            )
        } else {
            StringConstants.OUTPUT_MATCHING_COUNT.format(lottoPrize.matchingCount, lottoPrize.amount, matchingCount)
        }

}
