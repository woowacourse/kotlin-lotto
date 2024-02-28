package lotto.view

import lotto.model.Lotto
import lotto.model.PurchaseDetails
import lotto.model.Rank
import lotto.model.Result

object OutputView {
    fun printLottos(
        manualBuyedLottos: List<Lotto>,
        autoBuyedLottos: List<Lotto>,
    ) {
        println("\n수동으로 ${manualBuyedLottos.size}장, 자동으로 ${autoBuyedLottos.size}개를 구매했습니다.")
        val totalBuyedLottos = manualBuyedLottos + autoBuyedLottos
        totalBuyedLottos.forEach { lotto ->
            println(lotto.lottoNumbers.numbers)
        }
        println()
    }

    fun printResult(result: Result) {
        println("\n당첨 통계\n---------")
        Rank.entries
            .reversed()
            .filterNot { it == Rank.MISS }
            .forEach { rank ->
                val count = result.getWinningCountByRank(rank)
                printRankResult(rank, count)
            }
    }

    private fun printRankResult(
        rank: Rank,
        count: Int,
    ) {
        println(
            "${rank.countOfMatch}개 일치${if (rank.matchBonus == true) ", 보너스 볼 일치" else ""}" +
                " (${rank.winningMoney}원) - ${count}개",
        )
    }

    fun printProfitRate(
        result: Result,
        purchaseDetails: PurchaseDetails,
    ) {
        println(
            "총 수익률은 ${
                String.format(
                    "%.2f",
                    result.calculateProfitRate(purchaseDetails.calculateTotalPurchaseCost()),
                )
            }입니다.",
        )
    }
}
