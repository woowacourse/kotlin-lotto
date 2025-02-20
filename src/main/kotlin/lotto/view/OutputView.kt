package lotto.view

import lotto.Rank
import lotto.model.Lotto
import java.math.RoundingMode
import java.text.DecimalFormat

class OutputView {
    fun printPurchaseCount(count: Int) {
        println("${count}개를 구매했습니다.")
    }

    fun printLotto(lotto: Lotto) {
        println(lotto.getNumbers().map { it.number }.joinToString(", ", "[", "]"))
    }

    fun printResult(results: Map<Rank, Int>) {
        println("\n당첨 통계")
        println("---------")
        for (result in results) {
            val matchMessage =
                when (result.key) {
                    Rank.FIRST -> "6개 일치"
                    Rank.SECOND -> "5개 일치, 보너스 볼 일치"
                    Rank.THIRD -> "5개 일치"
                    Rank.FOURTH -> "4개 일치"
                    Rank.FIFTH -> "3개 일치"
                    Rank.MISS -> "0개 일치"
                }
            val prizeMessage =
                when (result.key) {
                    Rank.FIRST -> "2000000000원"
                    Rank.SECOND -> "30000000원"
                    Rank.THIRD -> "1500000원"
                    Rank.FOURTH -> "50000원"
                    Rank.FIFTH -> "5000원"
                    Rank.MISS -> "0원"
                }

            println("$matchMessage ($prizeMessage) - ${result.value}개")
        }
    }

    fun printProfit(profit: Double) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val formattedProfit = df.format(profit)
        println("총 수익률은 ${formattedProfit}입니다.")
    }
}
