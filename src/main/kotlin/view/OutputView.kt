package lotto.view

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

    companion object {
        const val MESSAGE_PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다."
    }
}