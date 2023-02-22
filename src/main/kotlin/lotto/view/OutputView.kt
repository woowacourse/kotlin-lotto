package lotto.view

import lotto.domain.Lotto
import lotto.domain.Rank

object OutputView {
    private const val INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요."
    private const val LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다."
    private const val INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
    private const val INPUT_WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요."
    private const val RESULT_MESSAGE = "당첨 통계\n-----------------"
    private const val FIFTH_PRIZE_MESSAGE = "%d개 일치 (%d원) - %d개"
    private const val FOURTH_PRIZE_MESSAGE = "%d일치 (%d원) - %d개"
    private const val THIRD_PRIZE_MESSAGE = "%d개 일치 (%d원) - %d개"
    private const val SECOND_PRIZE_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원) - %d개"
    private const val FIRST_PRIZE_MESSAGE = "%d개 일치 (%d원) - %d개"
    private const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."

    fun printInputMoneyPrompt() {
        println(INPUT_MONEY_PROMPT)
    }

    fun printLottoCountMessage(manualLottoCount: Int, autoMaticLottoCount: Int) {
        println(LOTTO_COUNT_MESSAGE.format(manualLottoCount, autoMaticLottoCount))
    }

    fun printInputManualLottoCountPrompt() {
        println(INPUT_MANUAL_LOTTO_COUNT)
    }

    fun printInputManualLottoNumbersPrompt() {
        println(INPUT_MANUAL_LOTTO_NUMBERS)
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
            println(
                prize.first.format(
                    prize.second.countOfMatch,
                    prize.second.winningMoney,
                    ranks.count { rank -> rank == prize.second }
                )
            )
        }
    }

    private fun printYield(yield: Double) {
        println(YIELD_MESSAGE.format(yield))
    }
}
