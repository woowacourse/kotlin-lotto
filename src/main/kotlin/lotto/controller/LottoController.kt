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
        val manualPurchase = inputView.inputManualPurchase()
        val count = LottoTicketCounter(purchase, manualPurchase).manualCount()
        val manual = inputView.inputManualLotto(count[0])
        outputView.printLottoCount(count)
        val manualLottoBundle = outputView.printManualLottoBundle(count[0], manual)
        val lottoBundle = outputView.printLottoBundle(count[1])
        val allLottoBundle = lottoBundle + manualLottoBundle
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        val winningStatistics = WinningStatistics.calculateStatistics(allLottoBundle, winningLotto)
        outputView.printStatistics(winningStatistics)
        val profit = Profit.calculateProfit(purchase, winningStatistics)
        outputView.printProfit(profit)
    }

    private fun getWinningLotto(): Lotto = Lotto(inputView.inputWinningNumbers())

    private fun getBonusNumber(): LottoNumber = LottoNumber(inputView.inputBonusNumber())
}
