package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoNumber
import lotto.model.LottoStore
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

    private fun getBonusNumber(): LottoNumber = LottoNumber.from(inputView.inputBonusNumber())

    private fun getLottoBundle(
        purchase: Double,
        manualCount: Int,
    ): List<Lotto> {
        val autoCount = LottoCashier(purchase, manualCount).autoCount()
        val manualLottoGenerator = ManualLottoGenerator()

        repeat(manualCount) {
            val manualLotto = inputView.inputManualLotto()
            manualLottoGenerator.add(manualLotto)
        }

        val manualLottoBundle = LottoStore().getTickets(manualCount, manualLottoGenerator)

        outputView.printLottoCount(manualCount, autoCount)

        val autoLottoBundle = LottoStore().getTickets(autoCount, RandomLottoGenerator())
        return manualLottoBundle + autoLottoBundle
    }
}
