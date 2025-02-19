package lotto.controller

import lotto.Purchase
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price = inputView.inputPurchasePrice()
        outputView.printLottoAmount(Purchase(price).calculateAmountOfLottos())
    }
}
