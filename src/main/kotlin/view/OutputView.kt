package view

import model.Lotto
import model.Rank

object OutputView {
    private const val LOTTO_PURCHASED = "%d개를 구매했습니다."

    fun printPurchasedLotto(lottos: List<Lotto>) {
        println(LOTTO_PURCHASED.format(lottos.size))
        lottos.forEach {
            print(it.toString())
        }
        println()
    }

    fun printStats(stats: Map<Rank, Int>) {
        Rank.entries.filter { it != Rank.MISS }.forEach {
            println("${it.countOfMatch}개 일치 (${it.winningMoney}원) - ${stats[it] ?: 0}")
        }
    }

    fun printProfit(profit: Double) {
        println("총 수익률은 $profit% 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임")
    }
}
