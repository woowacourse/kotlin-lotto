package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.LottoTicketCounter
import lotto.model.Profit
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.model.generator.RandomLottoGenerator
import lotto.view.LottoInputView
import lotto.view.LottoOutputView

class LottoController {
    private val inputView = LottoInputView()
    private val outputView = LottoOutputView()

    fun play() {
        val purchase = inputView.inputPurchase()
        val manualPurchase = inputView.inputManualPurchase()

        val allLottoBundle = getLottoBundle(purchase, manualPurchase)
        outputView.printLottoBundle(allLottoBundle)

        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        val winningStatistics = WinningStatistics.calculateStatistics(allLottoBundle, winningLotto)
        outputView.printStatistics(winningStatistics)

        val profit = Profit.calculateProfit(purchase, winningStatistics)
        outputView.printProfit(profit)
    }

    private fun getWinningLotto(): Lotto = Lotto(inputView.inputWinningNumbers())

    private fun getBonusNumber(): LottoNumber = LottoNumber(inputView.inputBonusNumber())

    private fun getLottoBundle(
        purchase: Double,
        manual: Int,
    ): List<Lotto> {
        val autoCount = LottoTicketCounter(purchase, manual).autoCount()
        outputView.printManualLottoGuide()
        val manualLottoInput = List(manual) { Lotto(inputView.inputManualLotto()) }
        outputView.printLottoCount(manual, autoCount)

        val lottoBundle = LottoStore().getTickets(autoCount, RandomLottoGenerator())
        return manualLottoInput + lottoBundle
    }
}
