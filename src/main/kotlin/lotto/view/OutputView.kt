package lotto.view

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoPrize

class OutputView {
    fun printPurchaseLotto(
        lottoCount: LottoCount,
        lottos: List<Lotto>,
    ) {
        println("수동으로 ${lottoCount.manualCount}장, 자동으로 ${lottoCount.autoCount}장 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto)
        }
        println()
    }

    fun printWinningResult(prizeCount: Map<LottoPrize, Int>) {
        println(OUTPUT_WINNING_STATICS)
        println(OUTPUT_DIVIDER)

        LottoPrize.entries.forEach { lottoPrize ->
            if (lottoPrize == LottoPrize.NOTHING) return@forEach
            val matchingCount = prizeCount.getOrDefault(lottoPrize, DEFAULT_MATCHING_COUNT)
            println(provideMatchingMessage(lottoPrize, matchingCount))
        }
    }

    fun printWinningRatio(profitRatio: Double) {
        println(OUTPUT_PROFIT_RATIO.format(profitRatio.provideTwoDecimal()))
    }

    private fun provideMatchingMessage(
        lottoPrize: LottoPrize,
        matchingCount: Int,
    ): String {
        if (lottoPrize == LottoPrize.SECOND) {
            return OUTPUT_MATCHING_COUNT_BONUS.format(
                lottoPrize.matchingCount,
                lottoPrize.amount,
                matchingCount,
            )
        }

        return OUTPUT_MATCHING_COUNT.format(lottoPrize.matchingCount, lottoPrize.amount, matchingCount)
    }

    private fun Double.provideTwoDecimal() = "%.2f".format(this)

    companion object {
        private const val DEFAULT_MATCHING_COUNT = 0
        private const val OUTPUT_PURCHASE_COUNT = "%d개를 구매했습니다."
        private const val OUTPUT_MATCHING_COUNT = "%d개 일치 (%d원)- %d개"
        private const val OUTPUT_MATCHING_COUNT_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개"
        private const val OUTPUT_WINNING_STATICS = "당첨 통계"
        private const val OUTPUT_DIVIDER = "---------"
        private const val OUTPUT_PROFIT_RATIO = "총 수익률은 %s입니다."
    }
}
