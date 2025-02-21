package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.value.EarningRate
import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank

class OutputView {
    fun printPurchaseDetail(
        purchaseAmount: PurchaseAmount,
        lottos: List<Lotto>,
    ) {
        val quantity = purchaseAmount.getPurchaseQuantity()
        printPurchaseQuantity(quantity)
        printLottos(lottos)
    }

    fun printLottoResult(
        winningStats: Map<Rank, Int>,
        earningRate: EarningRate,
    ) {
        println(OUTPUT_LOTTO_RESULT)
        Rank.entries.reversed().forEach { rank ->
            val bonusText = if (rank == Rank.SECOND) OUTPUT_BONUS_BALL else ""
            val count = winningStats[rank] ?: 0
            println(
                OUTPUT_RANK_DETAIL.format(
                    rank.countOfMatch,
                    bonusText,
                    rank.winningMoney,
                    count,
                ),
            )
        }
        printEarningRate(earningRate)
    }

    private fun printPurchaseQuantity(quantity: Int) {
        println(OUTPUT_PURCHASE_QUANTITY.format(quantity))
    }

    private fun printLottos(lottos: List<Lotto>) {
        lottos.forEach {
            println(it.lottoNumbers.map { it.number })
        }
    }

    private fun printEarningRate(earningRate: EarningRate) {
        println(OUTPUT_EARNING_RATE.format(earningRate.rate))
    }

    companion object {
        private const val OUTPUT_PURCHASE_QUANTITY = "%d개를 구매했습니다."
        private const val OUTPUT_LOTTO_RESULT = "\n당첨 통계\n---------"
        private const val OUTPUT_RANK_DETAIL = "%d개 일치%s (%d원)- %d개"
        private const val OUTPUT_BONUS_BALL = ", 보너스 볼 일치"
        private const val OUTPUT_EARNING_RATE = "총 수익률은 %.2f입니다."
    }
}
