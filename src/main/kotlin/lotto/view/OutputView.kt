package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.Rank

class OutputView : LottoOutputView {

    fun printLottoCountMessage(manualCount: Int, autoCount: Int) {
        println(LOTTO_COUNT_MESSAGE.format(manualCount, autoCount))
    }

    fun printLottoNumbers(lottoNumbers: List<Lotto>) {
        lottoNumbers.forEach { lotto -> printLotto(lotto) }
    }

    fun printResult(ranks: List<Rank>, yield: Double) {
        printResultMessage()
        printMatchingCount(ranks)
        printYield(yield)
    }

    private fun printLotto(lotto: Lotto) {
        println("[${lotto.numbers.map { lottoNumber -> lottoNumber.number }.joinToString(", ")}]")
    }

    private fun printResultMessage() {
        println(RESULT_MESSAGE)
    }

    private fun printMatchingCount(ranks: List<Rank>) {
        val messages = listOf(
            Pair(FIFTH_PRIZE_MESSAGE, Rank.FIFTH),
            Pair(FOURTH_PRIZE_MESSAGE, Rank.FOURTH),
            Pair(THIRD_PRIZE_MESSAGE, Rank.THIRD),
            Pair(SECOND_PRIZE_MESSAGE, Rank.SECOND),
            Pair(FIRST_PRIZE_MESSAGE, Rank.FIRST)
        )
        messages.forEach { prize ->
            println(prize.first.format(ranks.count { rank -> rank == prize.second }))
        }
    }

    private fun printYield(yield: Double) {
        println(YIELD_MESSAGE.format(yield))
    }

    companion object {
        const val ERROR_PREFIX = "[ERROR] "
        private const val LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val RESULT_MESSAGE = "당첨 통계\n-----------------"
        private const val FIFTH_PRIZE_MESSAGE = "3개 일치 (5000원) - %d개"
        private const val FOURTH_PRIZE_MESSAGE = "4개 일치 (50000원) - %d개"
        private const val THIRD_PRIZE_MESSAGE = "5개 일치 (1500000원) - %d개"
        private const val SECOND_PRIZE_MESSAGE = "5개 일치, 보너스 볼 일치 (30000000원) - %d개"
        private const val FIRST_PRIZE_MESSAGE = "6개 일치 (2000000000원) - %d개"
        private const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
