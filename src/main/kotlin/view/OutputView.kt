package view

class OutputView {
    fun printErrorMessage(message: String?) {
        println(message)
    }

    fun printPurchasedLottoCount(count: Int) {
        println(MESSAGE_PURCHASE_LOTTO_COUNT.format(count))
    }

    fun printPurchasedLotto(purchasedLotto: String) {
        println(purchasedLotto)
    }

    fun printWinningResult(
        lottoResult: String,
        profitRate: String,
    ) {
        print(MESSAGE_WINNING_RESULT)
        println(lottoResult)
        print(MESSAGE_TOTAL_PROFIT_RATE.format(profitRate))
    }

    fun printLossMessage() {
        println(MESSAGE_LOSS)
    }

    companion object {
        const val MESSAGE_PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다.\n"
        const val MESSAGE_WINNING_RESULT = "당첨 통계\n---------"
        const val MESSAGE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        const val MESSAGE_TOTAL_PROFIT_RATE = "총 수익률은 %s입니다."
    }
}
