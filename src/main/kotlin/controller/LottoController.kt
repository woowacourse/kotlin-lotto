package controller

import view.InputView

class LottoController {
    fun start() {
        val amount = readAmount()
    }

    private fun readAmount() = InputView.readAmount()
}
