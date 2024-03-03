package lotto.view

import lotto.model.PurchaseInfo
import lotto.view.util.retryWhileNoException

class PurchaseInfoInputView {
    fun readLottoCount(): PurchaseInfo {
        val purchasePrice = readPurchasePrice()
        val manualLottoCount = readManualLottoCount()
        return PurchaseInfo(purchasePrice, LOTTO_PRICE, manualLottoCount)
    }

    private fun readPurchasePrice(): Int =
        retryWhileNoException {
            println("구입금액을 입력해 주세요.")
            val purchasePrice = readln()
            validatePositiveDigit(purchasePrice)
            purchasePrice.toInt()
        }.also { println() }

    private fun readManualLottoCount(): Int =
        retryWhileNoException {
            println("수동으로 구매할 로또 수를 입력해 주세요.")
            val manualLottoCount = readln()
            validatePositiveDigit(manualLottoCount)
            manualLottoCount.toInt()
        }.also { println() }

    private fun validatePositiveDigit(it: String) {
        require(it.toIntOrNull() != null && it.toInt() >= MINIMUM_DIGIT) {
            "올바른 금액을 입력해 주세요."
        }
    }

    companion object {
        private const val MINIMUM_DIGIT = 0
        private const val LOTTO_PRICE = 1000
    }
}
