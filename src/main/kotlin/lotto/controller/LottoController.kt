package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoTicketCounter
import lotto.model.Profit
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.view.LottoInputView
import lotto.view.LottoOutputView

class LottoController {
    private val inputView = LottoInputView()
    private val outputView = LottoOutputView()

    fun play() {
        val purchase = inputView.inputPurchase()
        val count = LottoTicketCounter(purchase).count()
        outputView.printLottoCount(count)
        val lottoBundle = outputView.printLottoBundle(count)
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        val winningStatistics = WinningStatistics.calculateStatistics(lottoBundle, winningLotto)
        outputView.printStatistics(winningStatistics)
        val profit = Profit.calculateProfit(purchase, winningStatistics)
        outputView.printProfit(profit)
    }

    private fun getWinningLotto(): Lotto = Lotto(inputView.inputWinningNumbers())

    private fun getBonusNumber(): LottoNumber = LottoNumber(inputView.inputBonusNumber())
}
