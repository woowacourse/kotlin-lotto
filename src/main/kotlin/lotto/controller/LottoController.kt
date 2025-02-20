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
        val lottoBundle = outputView.printLottoBundle(count)
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        outputView.printResult(winningLotto, purchase, lottoBundle)
    }

    private fun getWinningLotto(): Lotto = Lotto(inputView.inputWinningNumbers())

    private fun getBonusNumber(): Int = inputView.inputBonusNumber()
}
