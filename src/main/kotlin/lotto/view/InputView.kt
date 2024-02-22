package lotto.view

import lotto.model.PurchaseInfo

class InputView {

    fun readPurchasePrice() = retryWhileNoException {
        println("구입금액을 입력해 주세요.")
        val purchasePrice = readln()
        PurchaseInfo(purchasePrice)
    }

    private fun <T> retryWhileNoException(action: () -> T): T {
        return try {
            action()
        } catch (e: IllegalArgumentException) {
            println("$ERROR_MESSAGE ${e.localizedMessage}")
            action()
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "[ERROR]"
    }

}
