package controller

import model.Buyer
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val buyer = Buyer(purchaseAmount)
        OutputView.outputNumberOfLotto(buyer.numberOfLotto)
    }
}
