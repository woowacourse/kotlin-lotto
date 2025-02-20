package lotto.controller

import lotto.domain.value.PurchaseQuantity
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    fun runLotto() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val purchaseQuantity = PurchaseQuantity(purchaseAmount)
        outputView.printPurchaseQuantity(purchaseQuantity)
    }
}