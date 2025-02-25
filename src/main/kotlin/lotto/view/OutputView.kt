package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Lottos
import lotto.domain.ResultClassification
import kotlin.math.floor

object OutputView {
    fun requestPrice() {
        println(MESSAGE_ENTER_PRICE)
    }

    fun requestManualQuantity() {
        println()
        println(MESSAGE_ENTER_MANUAL_PURCHASE_QUANTITY)
    }

    fun requestManualNumbers() {
        println()
        println(MESSAGE_ENTER_MANUAL_LOTTO_NUMBERS)
    }

    fun showLottoCount(lottoCount: Int) {
        println()
        println("$lottoCount$MESSAGE_RESULT_PURCHASE_QUANTITY")
    }

    fun showLottos(lottos: Lottos) {
        lottos.value.forEach { lotto: Lotto ->
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

    fun showResult(
        resultTally: Map<LottoResult, Int>,
        profitRate: Double,
    ) {
        println()
        println(MESSAGE_RESULT_HEADER)
        LottoResult.entries.drop(1).forEach { entry: LottoResult -> println(makePrizeDescription(entry, resultTally)) }
        println(
            MESSAGE_RESULT_SUMMARY.format(
                floor(profitRate * 100) / 100,
                makeResultSummary(profitRate),
            ),
        )
    }

    private fun makePrizeDescription(
        lottoResult: LottoResult,
        resultTally: Map<LottoResult, Int>,
    ): String =
        MESSAGE_RESULT_PER_PRIZE.format(
            lottoResult.matchCount,
            if (lottoResult.requireBonus) MESSAGE_BONUS_NUMBER_DESCRIPTION else "",
            lottoResult.prize,
            resultTally[lottoResult],
        )

    private fun makeResultSummary(profitRate: Double): String =
        when (ResultClassification.from(profitRate)) {
            ResultClassification.PROFIT -> MESSAGE_PROFIT_RATE_PROFIT
            ResultClassification.LOSS -> MESSAGE_PROFIT_RATE_LOSS
            ResultClassification.BREAKEVEN -> MESSAGE_PROFIT_RATE_BREAKEVEN
        }

    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
    private const val MESSAGE_ENTER_MANUAL_PURCHASE_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val MESSAGE_ENTER_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

    private const val MESSAGE_RESULT_PURCHASE_QUANTITY = "개를 구매했습니다."
    private const val MESSAGE_RESULT_HEADER = "당첨 통계\n---------"
    private const val MESSAGE_RESULT_PER_PRIZE = "%s개 일치%s (%s원) - %s개"
    private const val MESSAGE_RESULT_SUMMARY = "총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 %s 의미임)"

    private const val MESSAGE_BONUS_NUMBER_DESCRIPTION = ", 보너스 볼 일치"
    private const val MESSAGE_PROFIT_RATE_PROFIT = "이득이라는"
    private const val MESSAGE_PROFIT_RATE_LOSS = "손해라는"
    private const val MESSAGE_PROFIT_RATE_BREAKEVEN = "본전이라는"
}
