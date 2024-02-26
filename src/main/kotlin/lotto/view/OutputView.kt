package lotto.view

import lotto.exception.Exceptions

object OutputView {
    private const val PURCHASE_DESCRIPTION_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val WINNING_STATISTICS_MESSAGE = "\n당첨 통계\n---------"
    private const val TOTAL_RETURN_MESSAGE = "총 수익률은 %.2f입니다."

    fun printLottoBundle(lottoBundleContents: String) {
        println(lottoBundleContents)
        println()
    }

    fun printResult(
        lottoResultContents: String,
        rate: Double,
    ) {
        println(WINNING_STATISTICS_MESSAGE)
        println(lottoResultContents)
        println(TOTAL_RETURN_MESSAGE.format(rate))
    }

    fun printLottoCount(
        lottoManualPurchaseCount: Int,
        randomLottoCount: Int,
    ) = println(PURCHASE_DESCRIPTION_MESSAGE.format(lottoManualPurchaseCount, randomLottoCount))

    fun printError(e: Throwable) {
        when (e) {
            is Exceptions.DuplicateNumbersException -> println(e.reason)
            is Exceptions.BonusNumberDuplicateException -> println(e.reason)
            is Exceptions.PurchaseAmountNotNaturalNumberException -> println(e.reason)
            is Exceptions.InvalidPurchaseAmountException -> println(e.reason)
            is Exceptions.LottoNumberOutOfRangeException -> println(e.reason)
            is Exceptions.InvalidNumberException -> println(e.reason)
            is Exceptions.ManualPurchaseCountNotNaturalNumberException -> println(e.reason)
            is Exceptions.ManualPurchaseCountTooLargeException -> println(e.reason)
            else -> println(e.message)
        }
    }
}
