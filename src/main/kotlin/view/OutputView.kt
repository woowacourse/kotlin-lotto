package view

import model.Lotto
import model.Rank

object OutputView {
    private const val LOTTO_PURCHASED = "%d개를 구매했습니다."
    private const val HEADER_STATS =
        "\n" +
            "당첨 통계\n" +
            "-----------"

    fun printPurchasedLotto(lottos: List<Lotto>) {
        println(LOTTO_PURCHASED.format(lottos.size))
        lottos.forEach {
            print(it.toString())
        }
        println()
    }

    fun printStats(stats: Map<Rank, Int>) {
        println(HEADER_STATS)
        Rank.entries.filter { it != Rank.MISS }.forEach {
            println("${it.getMessage()} - ${stats[it] ?: 0}개")
        }
    }

    fun printProfit(profit: Double) {
        println("총 수익률은 $profit 입니다.")
    }
}
