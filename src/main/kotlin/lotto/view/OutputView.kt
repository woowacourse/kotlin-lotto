package lotto.view

object OutputView {
    private const val GET_PURCHASE_MONEY_SCRIPT = "구입금액을 입력해 주세요."
    private const val GET_MAIN_LOTTO_NUMBERS_SCRIPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val GET_BONUS_LOTTO_NUMBER_SCRIPT = "보너스 볼을 입력해 주세요."
    private const val WINNING_STATS_SCRIPT = "당첨 통계\n---------"
    private const val PURCHASE_COUNT_SCRIPT = "%d개를 구매했습니다."
    private const val YIELD_RATE_SCRIPT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    fun printGetPurchaseMoneyScript() {
        println(GET_PURCHASE_MONEY_SCRIPT)
    }

    fun printGetMainLottoNumbersScript() {
        println(GET_MAIN_LOTTO_NUMBERS_SCRIPT)
    }

    fun printGetBonusLottoNumberScript() {
        println(GET_BONUS_LOTTO_NUMBER_SCRIPT)
    }

    fun printWinningStatsScript() {
        println(WINNING_STATS_SCRIPT)
    }

    fun printWinningResult(winningResult: String) {
        println(winningResult)
    }

    fun printPurchaseResult(lottoBunch: String, purchaseCount: Int) {
        printPurchaseCount(purchaseCount)
        printLottoBunch(lottoBunch)
    }

    fun printYieldRate(yieldRate: Double) {
        println(YIELD_RATE_SCRIPT.format(yieldRate))
    }

    private fun printLottoBunch(lottoBunch: String) {
        println(lottoBunch)
        println()
    }

    private fun printPurchaseCount(purchaseCount: Int) {
        println(PURCHASE_COUNT_SCRIPT.format(purchaseCount))
    }
}
