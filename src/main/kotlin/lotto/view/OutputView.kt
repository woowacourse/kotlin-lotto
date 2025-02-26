package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoResult
import lotto.domain.value.LottoCount
import lotto.enums.Rank

class OutputView {
    fun printPurchaseDetail(
        manualLottoCount: LottoCount,
        automaticLottoCount: LottoCount,
        lottos: List<Lotto>,
    ) {
        printPurchaseQuantity(manualLottoCount, automaticLottoCount)
        printLottos(lottos)
    }

    fun printLottoResult(
        lottoResult: LottoResult,
        earningRate: Double,
    ) {
        println(OUTPUT_LOTTO_RESULT)
        printLottoStats(lottoResult.winningStats)
        printEarningRate(earningRate)
    }

    private fun printPurchaseQuantity(
        manualLottoCount: LottoCount,
        automaticLottoCount: LottoCount,
    ) {
        println(OUTPUT_PURCHASE_QUANTITY.format(manualLottoCount.count, automaticLottoCount.count))
    }

    private fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            println(lotto.lottoNumbers.map { it.number }.sorted())
        }
    }

    private fun printLottoStats(lottoStats: Map<Rank, Int>) {
        Rank.entries.sortedBy { it.countOfMatch }.filter { it != Rank.MISS }.forEach { rank ->
            val count = lottoStats[rank] ?: 0
            val message = if (rank == Rank.SECOND) OUTPUT_RANK_DETAIL_BONUS else OUTPUT_RANK_DETAIL
            println(message.format(rank.countOfMatch, rank.winningMoney, count))
        }
    }

    private fun printEarningRate(earningRate: Double) {
        val message =
            if (earningRate < 1) {
                StringBuilder()
                    .append(OUTPUT_EARNING_RATE.format(earningRate))
                    .append(OUTPUT_RESULT_LOSS)
                    .toString()
            } else {
                OUTPUT_EARNING_RATE.format(earningRate)
            }
        println(message)
    }

    companion object {
        private const val OUTPUT_PURCHASE_QUANTITY = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val OUTPUT_LOTTO_RESULT = "\n당첨 통계\n---------"
        private const val OUTPUT_RANK_DETAIL = "%d개 일치 (%d원) - %d개"
        private const val OUTPUT_RANK_DETAIL_BONUS = "%d개 일치, 보너스 볼 일치 (%d원) - %d개"
        private const val OUTPUT_EARNING_RATE = "총 수익률은 %.2f입니다."
        private const val OUTPUT_RESULT_LOSS = " (기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    }
}
