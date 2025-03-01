package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

class OutputView {
    fun printError(message: String) {
        println("[ERROR] $message")
    }

    fun printLottoCounts(
        manual: Int,
        auto: Int,
    ) {
        println("수동으로 ${manual}장, 자동으로 ${auto}개를 구매했습니다.")
    }

    fun printAllLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            println(lotto.numbers.map { it.toString() }.sorted())
        }
    }

    fun printResult(
        matchResults: Map<Rank, Int>,
        profitRate: String,
    ) {
        println(OUTPUT_STATISTIC_GUIDE_MESSAGE)

        val ranksInOrder = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
        ranksInOrder.forEach { rank ->
            val bonusMessage = if (rank == Rank.SECOND) ", 보너스 볼 일치" else ""
            println("${rank.countOfMatch}개 일치$bonusMessage (${rank.winningMoney}원)- ${matchResults[rank] ?: 0}개")
        }

//        Rank.entries.filterNot { it == Rank.MISS }.forEach { rank ->
//            println(
//                "${rank.countOfMatch}개 일치${if (rank == Rank.SECOND)OUTPUT_STATISTICS_BONUS_NUMBER_MESSAGE else ""} (${rank.winningMoney}원)- ${matchResults[rank] ?: 0}개",
//            )
//        }

        println("총 수익률은 $profitRate 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)")
    }

    companion object {
        private const val OUTPUT_STATISTIC_GUIDE_MESSAGE = "\n당첨 통계\n---------"
        private const val OUTPUT_STATISTICS_MESSAGE = "%d개 일치%s(%d원)- %d개"
        private const val OUTPUT_STATISTICS_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치"
        private const val OUTPUT_PROFIT_MESSAGE = "총 수익률은 %s입니다."
    }
}
