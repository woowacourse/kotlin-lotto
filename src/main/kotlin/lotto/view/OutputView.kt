package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

class OutputView {
    fun printManualLotto(count: Int) {
        print("수동으로 ${count}장, ")
    }

    fun printPublishedLotto(publishedLotto: List<Lotto>) {
        println("자동으로 ${publishedLotto.size}장를 구매했습니다.")
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
        Rank.entries.sortedBy { it.printSequence }.filter { it.printSequence != null }.forEach { rank ->
            print("${rank.countOfMatch}개 일치")
            if (rank.bonusRequired) print(", 보너스 볼 일치")
            print(" (${rank.winningMoney}원) - ${result.getOrDefault(rank,0)}개")
            println()
        }
        println("총 수익률은 ${String.format("%.2f", earningRate)}입니다.")
    }
}
