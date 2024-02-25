package lotto.view

import lotto.exception.Exceptions
import lotto.model.LottoBundle
import lotto.model.LottoResult

object OutputView {
    private const val WINNING_STATISTICS_MESSAGE = "\n당첨 통계\n---------"
    private const val TOTAL_RETURN_MESSAGE = "총 수익률은 %.2f입니다."

    fun printLottoBundle(lottoBundle: LottoBundle) {
        println(LOTTO_SIZE_MESSAGE.format(lottoBundle.lottos.size))
        println(lottoBundle)
        println()
    }

    fun printResult(lottoResult: LottoResult) {
        println(WINNING_STATISTICS_MESSAGE)
        println(lottoResult)
        println(TOTAL_RETURN_MESSAGE.format(lottoResult.getProfitRate()))
    }

    fun printError(e: Throwable) {
        when (e) {
            is Exceptions.DuplicateNumbersException -> println(e.reason)
            is Exceptions.BonusNumberDuplicateException -> println(e.reason)
            is Exceptions.PurchaseAmountNotNaturalNumberException -> println(e.reason)
            is Exceptions.InvalidPurchaseAmountException -> println(e.reason)
            is Exceptions.LottoNumberOutOfRangeException -> println(e.reason)
            is Exceptions.InvalidNumberException -> println(e.reason)
            else -> println(e.message)
        }
    }
}
