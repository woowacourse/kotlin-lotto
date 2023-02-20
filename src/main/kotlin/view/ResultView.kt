package view

import domain.lotto.AutoLotto
import domain.lotto.ManualLotto
import domain.rank.Rank

class ResultView {
    fun printPurchasedLottos(manualLottos: List<ManualLotto>, autoLottos: List<AutoLotto>) {
        println()
        println(PURCHASED_LOTTO_SIZE_FORMAT.format(manualLottos.size, autoLottos.size))
        (manualLottos + autoLottos).map { it.getSortedLotto() }.forEach { sortedNumber ->
            println(sortedNumber.map { it.value }.joinToString(", ", "[", "]"))
        }
        println()
    }

    fun printIncomeStatics(resultRank: Map<Rank, Int>, incomeRate: Double) {
        printStaticsHead()
        printLottoMatchResult(resultRank)
        printIncomeRate(incomeRate)
    }

    private fun printLottoMatchResult(resultRank: Map<Rank, Int>) {
        Rank.values().slice(0 until Rank.values().size - 1).reversed().forEach { rank ->
            println(RANK_PRINT_FORMAT.format(rank.countOfMatch, rank.winningMoney, resultRank[rank] ?: DEFAULT_MATCHED_SIZE))
        }
    }

    private fun printStaticsHead() {
        println()
        println(WINNING_RATE_TITLE)
        println(DIVIDER)
    }

    private fun printIncomeRate(incomeRate: Double) {
        print(TOTAL_INCOME_RATE_FORMAT.format(incomeRate))
        if (incomeRate < LOSS_RATE_STANDARD) println(LOSS_MESSAGE)
    }

    companion object {
        private const val PURCHASED_LOTTO_SIZE_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."

        private const val WINNING_RATE_TITLE = "당첨 통계"
        private const val DIVIDER = "---------"
        private const val RANK_PRINT_FORMAT = "%d개 일치 (%d원) - %d개"
        private const val TOTAL_INCOME_RATE_FORMAT = "총 수익률은 %.2f입니다."
        private const val LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

        private const val DEFAULT_MATCHED_SIZE = 0
        private const val LOSS_RATE_STANDARD = 1.0F
    }
}
