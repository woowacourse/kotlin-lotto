package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoTicketCounter
import lotto.model.WinningLotto
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
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
    }

    private fun getWinningLotto(): Lotto {
        return Lotto(inputView.inputWinningNumbers())
    }

    private fun getBonusNumber(): Int {
        return inputView.inputBonusNumber()
    }
}

fun main() {
    LottoController().play()
}
