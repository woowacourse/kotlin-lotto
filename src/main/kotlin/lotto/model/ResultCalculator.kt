package lotto.model

import lotto.constants.LottoPrize

object ResultCalculator {

    fun calculatePrize(lottoStore: LottoStore, winningLotto: Lotto, bonusNumber: LottoNumber) =
        lottoStore.lottos
            .map { lotto -> lotto.compare(winningLotto, bonusNumber) }
            .groupBy { it }
            .mapValues { it.value.size }

    fun calculateProfitRatio(purchaseInfo: PurchaseInfo, prizeCount: Map<LottoPrize, Int>): Double {
        val totalPrizeAmount = prizeCount
            .map { (lottoPrize, count) ->
                lottoPrize.amount * count
            }.sum()

        return totalPrizeAmount.toDouble() / purchaseInfo.price * 100
    }

}
