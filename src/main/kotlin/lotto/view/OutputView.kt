package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

object OutputView {
    fun printLottos(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto.numbers)
        }
        println()
    }

    fun printResult(result: Map<Rank, Int>) {
        println("당첨 통계")
        println("---------")
        Rank.entries.reversed().forEach { rank ->
            val count = result[rank] ?: 0
            when (rank) {
                Rank.MISS -> {}
                Rank.SECOND -> println("${rank.countOfMatch}개 일치, 보너스 볼 일치(${rank.winningMoney})- ${count}개")
                else -> println("${rank.countOfMatch}개 일치 (${rank.winningMoney})- ${count}개")
            }
        }
    }
}
