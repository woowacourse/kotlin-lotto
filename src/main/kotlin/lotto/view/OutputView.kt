package lotto.view

import lotto.model.Lotto
import lotto.model.LottoPrize
import lotto.model.PurchaseInfo

object OutputView {
    private const val DEFAULT_MATCHING_COUNT = 0

    fun printPurchaseLotto(
        purchaseInfo: PurchaseInfo,
        lottos: List<Lotto>,
    ) {
        println("수동으로 ${purchaseInfo.manualCount}장, 자동으로 ${purchaseInfo.autoCount}장 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto)
        }
        println()
    }

    fun printWinningResult(prizeCount: Map<LottoPrize, Int>) {
        println("당첨 통계")
        println("---------")

        LottoPrize.entries.forEach { lottoPrize ->
            if (lottoPrize == LottoPrize.NOTHING) return@forEach
            val matchingCount = prizeCount.getOrDefault(lottoPrize, DEFAULT_MATCHING_COUNT)
            println(provideMatchingMessage(lottoPrize, matchingCount))
        }
    }

    fun printWinningRatio(profitRatio: Double) {
        println("총 수익률은 ${profitRatio.provideTwoDecimal()}입니다.")
    }

    private fun provideMatchingMessage(
        lottoPrize: LottoPrize,
        matchingCount: Int,
    ): String {
        if (lottoPrize == LottoPrize.SECOND) {
            return "${lottoPrize.matchingCount}개 일치, 보너스 볼 일치(${lottoPrize.amount}원) - ${matchingCount}개"
        }

        return "${lottoPrize.matchingCount}개 일치 - ${matchingCount}개"
    }

    private fun Double.provideTwoDecimal() = "%.2f".format(this)
}
