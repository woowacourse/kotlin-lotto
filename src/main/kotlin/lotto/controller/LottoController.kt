package lotto.controller

import lotto.model.LottoTicketCounter
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun play() {
        val purchase = inputView.inputPurchase()
        val count = LottoTicketCounter(purchase).count()
        outputView.printLottoCount(count)
        outputView.printLottoBundle(count)
    }
}

fun main() {
    LottoController().play()
}
