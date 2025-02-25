package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.LottoTicketCounter
import lotto.model.Profit
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.model.generator.ManualLottoGenerator
import lotto.model.generator.RandomLottoGenerator
import lotto.view.LottoInputView
import lotto.view.LottoOutputView

class LottoController {
    private val inputView = LottoInputView()
    private val outputView = LottoOutputView()
    private val manualLottoGenerator = ManualLottoGenerator()

    fun play() {
        val purchase = inputView.inputPurchase()
        val manualPurchase = inputView.inputManualPurchase()
        val count = LottoTicketCounter(purchase, manualPurchase).manualCount()
        val manualLotto = inputView.inputManualLotto(count[0], manualLottoGenerator)
        outputView.printLottoCount(count)

        val lottoBundle = LottoStore().getTickets(count[1], RandomLottoGenerator())
        val manualLottoBundle = LottoStore().getTickets(count[0], manualLotto)
        outputView.printManualLottoBundle(manualLottoBundle)
        outputView.printLottoBundle(lottoBundle)

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
