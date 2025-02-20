package lotto.view

import lotto.model.Lotto
import lotto.model.LottoResult

class OutputView {
    fun printPurchaseCount(count: Int) {
        println("${count}개를 구매했습니다.")
    }

    fun printLotto(lotto: Lotto) {
        println(lotto.getNumbers().map { it.number }.joinToString(", ", "[", "]"))
    }

    fun printResult(results: LottoResult) {
        println("\n당첨 통계")
        println("---------")
    }

    fun printProfit(profit: Double) {
        println("총 수익률은 ${profit}입니다.")
    }
}
