package lotto.view

import lotto.model.Lotto
import lotto.model.LottoRank
import lotto.model.LottoResult

object OutputView {
    fun printLottos(
        manualBuyedLottos: List<Lotto>,
        autoBuyedLottos: List<Lotto>,
    ) {
        println("\n수동으로 ${manualBuyedLottos.size}장, 자동으로 ${autoBuyedLottos.size}개를 구매했습니다.")
        val totalBuyedLottos = manualBuyedLottos + autoBuyedLottos
        totalBuyedLottos.forEach { lotto ->
            println(lotto.lottoNumbers)
        }
        println()
    }

    fun printResult(lottoResult: LottoResult) {
        println("\n당첨 통계\n---------")
        lottoResult.winningCountsByLottoRank.filterNot { it.key == LottoRank.MISS }
            .forEach { (lottoRank, count) ->
                println(
                    "${lottoRank.countOfMatch}개 일치${if (lottoRank.matchBonus == true) ", 보너스 볼 일치" else ""}" +
                        " (${lottoRank.winningMoney}원) - ${count}개",
                )
            }
        println("총 수익률은 ${String.format("%.2f", lottoResult.getProfitRate())}입니다.")
    }
}
