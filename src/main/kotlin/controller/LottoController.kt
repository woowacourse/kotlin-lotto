package controller

import model.Buyer
import model.Lotto
import model.Lottos
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val buyer = Buyer(purchaseAmount)
        val lottos = Lottos(List(buyer.numberOfLotto) { Lotto() })
        displayPurchaseResult(buyer, lottos)
    }

    fun displayPurchaseResult(buyer: Buyer, lottos: Lottos) {
        OutputView.outputNumberOfLotto(buyer.numberOfLotto)
        OutputView.outputLottos(lottos)
    }
}
