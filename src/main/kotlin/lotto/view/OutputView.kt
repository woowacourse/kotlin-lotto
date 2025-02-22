package lotto.view

import lotto.model.Rank

class OutputView {
    fun printAmountMessage() {
        println("구입금액을 입력해 주세요.")
    }

    fun printPublishedLotto(
        quantity: Int,
        publishedLotto: List<String>,
    ) {
        println("${quantity}개를 구매했습니다.")
        publishedLotto.forEach { println(it) }
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
        Rank.entries.forEach { rank ->
            if (rank.winningMoney > 0) {
                println(
                    "${rank.countOfMatch}개 일치${if (rank == Rank.SECOND) ", 보너스 볼 일치" else ""} (${rank.winningMoney}원) - ${result[rank]}개",
                )
            }
        }
        println("총 수익률은 ${"%.2f".format(earningRate)}입니다.")
    }
}
