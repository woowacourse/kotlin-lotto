package lotto.view

import kotlin.math.floor

object OutputView {
    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val MESSAGE_RESULT_HEADER = "당첨 통계\n---------"
    private const val MESSAGE_BOUGHT = "개를 구매했습니다."
    private const val MESSAGE_PROFIT_RATE_LOSE = "손해라는"
    private const val MESSAGE_PROFIT_RATE_DRAW = "본전이라는"
    private const val MESSAGE_PROFIT_RATE_WIN = "이득이라는"
    private const val RESULT_DESCRIPTION_TEMPLATE = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %S 의미임)"
    private const val SHOW_LOTTO_SEPARATOR = "\n"
    private const val SHOW_LOTTO_RESULT_SEPARATOR = "\n"
    private const val PROFIT_RATE_DRAW = 1.0

    fun requestPrice() {
        println(MESSAGE_ENTER_PRICE)
    }

    fun showLottoCount(lottoCount: Int) {
        println("$lottoCount$MESSAGE_BOUGHT")
    }

    fun showLottos(numbers: List<List<Int>>) {
        println(numbers.joinToString(SHOW_LOTTO_SEPARATOR))
        println()
    }

    fun requestWinningLotto() {
        println(MESSAGE_ENTER_WINNING_NUMBERS)
    }

    fun requestBonusNumber() {
        println(MESSAGE_ENTER_BONUS_NUMBER)
    }

    fun showResult(
        userLottoResults: List<String>,
        profitRate: Double,
    ) {
        println()
        println(MESSAGE_RESULT_HEADER)
        println(userLottoResults.joinToString(SHOW_LOTTO_RESULT_SEPARATOR))
        println(
            RESULT_DESCRIPTION_TEMPLATE.format(
                floor(profitRate * 100) / 100,
                makeProfitRateDescription(profitRate),
            ),
        )
    }

    private fun makeProfitRateDescription(profitRate: Double): String =
        when {
            profitRate < PROFIT_RATE_DRAW -> MESSAGE_PROFIT_RATE_LOSE
            profitRate > PROFIT_RATE_DRAW -> MESSAGE_PROFIT_RATE_WIN
            else -> MESSAGE_PROFIT_RATE_DRAW
        }
}
