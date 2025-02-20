package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

class OutputView {
    fun printAmountMessage() {
        println("구입금액을 입력해 주세요.")
    }

    fun printPublishedLotto(
        quantity: Int,
        publishedLotto: List<Lotto>,
    ) {
        println(quantity.toString() + "개를 구매했습니다.")
        publishedLotto.forEach { lotto ->
            val numbers = lotto.numbers.map { it.value }
            println(numbers)
        }
    }

    fun printWinningNumberMessage() {
        println()
        println("지난 주 당첨 번호를 입력해 주세요.")
    }

    fun printBonusMessage() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun printPrize(
        result: Map<Rank, Int>,
        earningRate: Double,
    ) {
        println()
        println("당첨 통계")
        println("---------")
        println("3개 일치 (5000원) - ${result[Rank.FIFTH]}개")
        println("4개 일치 (50000원) - ${result[Rank.FOURTH]}개")
        println("5개 일치 (1500000원) - ${result[Rank.THIRD]}개")
        println("5개 일치, 보너스 볼 일치 (30000000원) - ${result[Rank.SECOND]}개")
        println("6개 일치 (2000000000원) - ${result[Rank.FIRST]}개")
        println("총 수익률은 ${String.format("%.2f", earningRate)}입니다.")
    }
}
