package lotto.controller

import lotto.view.InputView
class LottoController(private val inputView: InputView) {
    fun runLotto() {
        inputView.readPurchaseAmount()
    }
}