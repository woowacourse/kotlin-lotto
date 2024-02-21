package lotto.view

import lotto.model.Lotto
import lotto.model.LottoResult
import lotto.model.Rank

object OutputView {
    fun printLottos(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto.lottoNumbers.map { it.number })
        }
        println()
    }

    fun printResult(lottoResult: LottoResult) {
        println("\n당첨 통계\n---------")
        Rank.entries.reversed().forEach { rank ->
            val count = lottoResult.static[rank] ?: 0
            when (rank) {
                Rank.MISS -> Unit
                Rank.SECOND -> println("${rank.countOfMatch}개 일치, 보너스 볼 일치(${rank.winningMoney})- ${count}개")
                else -> println("${rank.countOfMatch}개 일치 (${rank.winningMoney})- ${count}개")
            }
        }
        println("총 수익률은 ${String.format("%.2f", lottoResult.getProfitRate())}입니다.")
    }
}
