package lotto.view

import lotto.domain.model.Lotto
import lotto.enums.Rank

class OutputView {
    fun printPurchaseDetail(lottos: List<Lotto>) {
        printPurchaseQuantity(lottos.size)
        printLottos(lottos)
    }

    fun printLottoResult(
        lottoStats: Map<Rank, Int>,
        earningRate: Double,
    ) {
        println(OUTPUT_LOTTO_RESULT)
        printLottoStats(lottoStats)
        printEarningRate(earningRate)
    }

    private fun printPurchaseQuantity(quantity: Int) {
        println(OUTPUT_PURCHASE_QUANTITY.format(quantity))
    }

    private fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            println(lotto.lottoNumbers.map { it.number })
        }
    }

    private fun printLottoStats(lottoStats: Map<Rank, Int>) {
        Rank.entries.reversed().forEach { rank ->
            val count = lottoStats[rank] ?: 0
            val message = if (rank == Rank.SECOND) OUTPUT_RANK_DETAIL_BONUS else OUTPUT_RANK_DETAIL
            println(message.format(rank.countOfMatch, rank.winningMoney, count))
        }
    }

    private fun printEarningRate(earningRate: Double) {
        println(OUTPUT_EARNING_RATE.format(earningRate))
    }

    companion object {
        private const val OUTPUT_PURCHASE_QUANTITY = "%d개를 구매했습니다."
        private const val OUTPUT_LOTTO_RESULT = "\n당첨 통계\n---------"
        private const val OUTPUT_RANK_DETAIL = "%d개 일치 (%d원) - %d개"
        private const val OUTPUT_RANK_DETAIL_BONUS = "%d개 일치, 보너스 볼 일치 (%d원) - %d개"
        private const val OUTPUT_EARNING_RATE = "총 수익률은 %.2f입니다."
    }
}
