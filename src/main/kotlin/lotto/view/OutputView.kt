package lotto.view

import lotto.model.Lotto
import lotto.model.LottoRank
import lotto.model.LottoResult

object OutputView {
    fun printLottos(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto.lottoNumbers)
        }
        println()
    }

    fun printResult(lottoResult: LottoResult) {
        println("\n당첨 통계\n---------")
        lottoResult.winningCountsByLottoRank.filterNot { it.key == LottoRank.MISS }
            .forEach { (lottoRank, count) ->
                println("${lottoRank.description()} - ${count}개")
            }
        println("총 수익률은 ${String.format("%.2f", lottoResult.getProfitRate())}입니다.")
    }
}
