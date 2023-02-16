package lotto.view

import lotto.constants.Rank
import lotto.domain.Lotto

object OutputView {
    private const val INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요."
    private const val LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다."
    private const val INPUT_WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요."
    private const val RESULT_MESSAGE = "당첨 통계\n-----------------"
    private const val FIFTH_PRIZE_MESSAGE = "3개 일치 (5000원) - %d개"
    private const val FOURTH_PRIZE_MESSAGE = "4개 일치 (50000원) - %d개"
    private const val THIRD_PRIZE_MESSAGE = "5개 일치 (1500000원) - %d개"
    private const val SECOND_PRIZE_MESSAGE = "5개 일치, 보너스 볼 일치 (30000000원) - %d개"
    private const val FIRST_PRIZE_MESSAGE = "6개 일치 (2000000000원) - %d개"
    private const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."

    fun printInputMoneyPrompt() {
        println(INPUT_MONEY_PROMPT)
    }

    fun printLottoCountMessage(count: Int) {
        println(LOTTO_COUNT_MESSAGE.format(count))
    }

    fun printLottoNumbers(lottoNumbers: List<Lotto>) {
        lottoNumbers.forEach { lotto -> printLotto(lotto) }
    }

    private fun printLotto(lotto: Lotto) {
        println("[${lotto.numbers.map { lottoNumber -> lottoNumber.number }.joinToString(", ")}]")
    }

    fun printInputWinningNumbersPrompt() {
        println(INPUT_WINNING_NUMBERS_PROMPT)
    }

    fun printInputBonusNumberPrompt() {
        println(INPUT_BONUS_NUMBER_PROMPT)
    }

    fun printResult(ranks: List<Rank>, yield: Double) {
        printResultMessage()
        printMatchingCount(ranks)
        printYield(yield)
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
}