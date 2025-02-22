package lotto.view

import lotto.domain.LottoResult
import lotto.domain.ResultClassification
import kotlin.math.floor

object OutputView {
    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val MESSAGE_RESULT_HEADER = "당첨 통계\n---------"
    private const val MESSAGE_BOUGHT = "개를 구매했습니다."
    private const val MESSAGE_PROFIT_RATE_PROFIT = "이득이라는"
    private const val MESSAGE_PROFIT_RATE_LOSS = "손해라는"
    private const val MESSAGE_PROFIT_RATE_BREAKEVEN = "본전이라는"

    fun requestPrice() {
        println(MESSAGE_ENTER_PRICE)
    }

    fun showLottoCount(lottoCount: Int) {
        println("$lottoCount$MESSAGE_BOUGHT")
    }

    fun showLottos(numbers: List<List<Int>>) {
        println(numbers.joinToString("\n"))
        println()
    }

    fun requestWinningLotto() {
        println(MESSAGE_ENTER_WINNING_NUMBERS)
    }

    fun requestBonusNumber() {
        println(MESSAGE_ENTER_BONUS_NUMBER)
    }

    fun showResult(
        resultTally: Map<LottoResult, Int>,
        profitRate: Double,
    ) {
        println()
        println(MESSAGE_RESULT_HEADER)
        LottoResult.entries.drop(1).forEach { entry ->
            println("${entry.matchCount}개 일치${getBonusBallDescription(entry)} (${entry.prizeAmount}원) - ${resultTally[entry]}개")
        }
        println("총 수익률은 ${floor(profitRate * 100) / 100}입니다.(기준이 1이기 때문에 결과적으로 ${resultDescription(profitRate)} 의미임)")
    }

    private fun getBonusBallDescription(entry: LottoResult): String =
        if (entry.bonusMatched == LottoResult.BonusMatched.YES) ", 보너스 볼 일치" else ""

    private fun resultDescription(profitRate: Double): String =
        when (ResultClassification.from(profitRate)) {
            ResultClassification.PROFIT -> MESSAGE_PROFIT_RATE_PROFIT
            ResultClassification.LOSS -> MESSAGE_PROFIT_RATE_LOSS
            ResultClassification.BREAKEVEN -> MESSAGE_PROFIT_RATE_BREAKEVEN
        }
}
