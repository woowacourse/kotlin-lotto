package view

import domain.model.Lotto
import domain.model.Lotto.Companion.toValues
import domain.model.LottoResult

class OutputView {
    fun printErrorMessage(message: String?) {
        println(message)
    }

    fun printPurchasedLottoCount(
        manualCount: Int,
        autoCount: Int,
    ) {
        println(MESSAGE_PURCHASE_LOTTO_COUNT.format(manualCount, autoCount))
    }

    fun printPurchasedLotto(purchasedLotto: List<Lotto>) {
        println(purchasedLotto.map { it.numbers.toValues().sorted() }.joinToString("\n"))
    }

    fun printWinningResult(
        lottoResult: LottoResult,
        profitRate: String,
    ) {
        println(lottoResult.toString())
        print(MESSAGE_TOTAL_PROFIT_RATE.format(profitRate))
    }

    fun printLossMessage() {
        println(MESSAGE_LOSS)
    }

    companion object {
        const val MESSAGE_PURCHASE_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        const val MESSAGE_WINNING_RESULT = "당첨 통계\n---------"
        const val MESSAGE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        const val MESSAGE_TOTAL_PROFIT_RATE = "총 수익률은 %s입니다."
    }
}
