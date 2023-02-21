package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoBunch

object OutputView {
    private const val WINNING_STATS_SCRIPT = "당첨 통계\n---------"
    private const val PURCHASE_COUNT_SCRIPT = "%d개를 구매했습니다."
    private const val YIELD_RATE_SCRIPT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    private const val LOTTO_BUNCH_PRINT_SEPARATOR = "\n"

    fun printWinningStatsScript() {
        println(WINNING_STATS_SCRIPT)
    }

    fun printWinningResult(winningResult: String) {
        println(winningResult)
    }

    fun printPurchaseResult(lottoBunch: LottoBunch, purchaseCount: Int) {
        printPurchaseCount(purchaseCount)
        printLottoBunch(lottoBunch)
    }

    fun printYieldRate(yieldRate: Double) {
        println(YIELD_RATE_SCRIPT.format(yieldRate))
    }

    private fun printLottoBunch(lottoBunch: LottoBunch) {
        print(
            lottoBunch.value.map { lotto ->
                convertLottoBunchToPrintForm(lotto)
            }.joinToString(separator = LOTTO_BUNCH_PRINT_SEPARATOR),
        )
        println()
    }

    private fun convertLottoBunchToPrintForm(lotto: Lotto): List<Int> =
        lotto.lottoNumbers.sortedBy { lottoNumber -> lottoNumber.value }.map { it.value }

    private fun printPurchaseCount(purchaseCount: Int) {
        println(PURCHASE_COUNT_SCRIPT.format(purchaseCount))
    }
}
