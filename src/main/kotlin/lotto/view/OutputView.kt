package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

class OutputView {
    fun printPublishedLotto(publishedLotto: List<Lotto>) {
        println("${publishedLotto.size}개를 구매했습니다.")
        publishedLotto.forEach { lotto ->
            val numbers = lotto.numbers.numberList.map { it.value }
            println(numbers)
        }
    }

    fun printPrize(
        result: Map<Rank, Int>,
        earningRate: Double,
    ) {
        println()
        println("당첨 통계")
        println("---------")
        result.entries.sortedBy { it.key.winningMoney }.filter { it.key != Rank.MISS }.forEach { (rank, value) ->
            print("${rank.countOfMatch}개 일치")
            if (rank.name == "SECOND") print(", 보너스 볼 일치")
            print(" (${rank.winningMoney}원) - ${value}개")
            println()
        }
        println("총 수익률은 ${String.format("%.2f", earningRate)}입니다.")
    }
}
