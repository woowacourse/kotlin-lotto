package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Result
import lotto.domain.Results

object OutputView {
    fun requestPayment() {
        println(MESSAGE_ENTER_PAYMENT)
    }

    fun requestManualQuantity() {
        println()
        println(MESSAGE_ENTER_MANUAL_PURCHASE_QUANTITY)
    }

    fun requestManualNumbers() {
        println()
        println(MESSAGE_ENTER_MANUAL_LOTTO_NUMBERS)
    }

    fun showLottoCount(
        manualQuantity: Int,
        automaticQuantity: Int,
    ) {
        println()
        println(MESSAGE_RESULT_PURCHASE_QUANTITY.format(manualQuantity, automaticQuantity))
    }

    fun showLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto: Lotto ->
            println(lotto.numbers.map(LottoNumber::value).sorted())
        }
    }

    fun requestWinningLotto() {
        println()
        println(MESSAGE_ENTER_WINNING_NUMBERS)
    }

    fun requestBonusNumber() {
        println()
        println(MESSAGE_ENTER_BONUS_NUMBER)
    }

    fun showResults(results: Results) {
        println()
        println(MESSAGE_RESULT_HEADER)
        Result.entries.sortedWith(compareBy(Result::matchCount, Result::requireBonus)).forEach { result: Result ->
            if (result != Result.FAIL) println(makePrizeDescription(result, results.tally[result] ?: 0))
        }
        println(
            MESSAGE_RESULT_SUMMARY.format(
                "%.2f".format(results.profitRate),
                makeResultSummary(results.classification),
            ),
        )
    }

    private fun makePrizeDescription(
        result: Result,
        count: Int,
    ): String =
        MESSAGE_RESULT_PER_PRIZE.format(
            result.matchCount,
            if (result.requireBonus) MESSAGE_BONUS_NUMBER_DESCRIPTION else "",
            result.prize,
            count,
        )

    private fun makeResultSummary(classification: Results.Classification): String =
        when (classification) {
            Results.Classification.PROFIT -> MESSAGE_PROFIT_RATE_PROFIT
            Results.Classification.LOSS -> MESSAGE_PROFIT_RATE_LOSS
            Results.Classification.BREAKEVEN -> MESSAGE_PROFIT_RATE_BREAKEVEN
        }

    private const val MESSAGE_ENTER_PAYMENT = "구입금액을 입력해 주세요."
    private const val MESSAGE_ENTER_MANUAL_PURCHASE_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val MESSAGE_ENTER_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

    private const val MESSAGE_RESULT_PURCHASE_QUANTITY = "수동으로 %s장, 자동으로 %s장을 구매했습니다."
    private const val MESSAGE_RESULT_HEADER = "당첨 통계\n---------"
    private const val MESSAGE_RESULT_PER_PRIZE = "%s개 일치%s (%s원) - %s개"
    private const val MESSAGE_RESULT_SUMMARY = "총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 %s 의미임)"

    private const val MESSAGE_BONUS_NUMBER_DESCRIPTION = ", 보너스 볼 일치"
    private const val MESSAGE_PROFIT_RATE_PROFIT = "이득이라는"
    private const val MESSAGE_PROFIT_RATE_LOSS = "손해라는"
    private const val MESSAGE_PROFIT_RATE_BREAKEVEN = "본전이라는"
}
