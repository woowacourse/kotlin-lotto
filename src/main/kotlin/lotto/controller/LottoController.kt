package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.WinningLotto
import lotto.model.lottomachine.LottoMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoMachine: LottoMachine,
) {
    fun run() {
        val purchaseMoney = getPurchaseMoney()
        val lottoCount = getLottoCount(purchaseMoney)
        outputView.printLottoCount(lottoCount)
        val lottos = lottoMachine.createLottos(lottoCount)
        outputView.printLottos(lottos)

        val winningLotto = getWinningLotto()
        val lottoStatistics: LottoStatistics = winningLotto.calculateStatistics(lottos, purchaseMoney)

        processLottoStatistics(lottoStatistics)
    }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        try {
            outputView.printPurchaseAmountGuide()
            inputView.readLottoPurchaseAmount()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getPurchaseMoney()
        }

    private fun getLottoCount(purchaseMoney: LottoPurchaseAmount): Int {
        val lottoCount = purchaseMoney.getLottoCount()
        return lottoCount
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers = getWinningLottoNumbers()
        val winningLotto = getWinningLotto(winningLottoNumbers)
        return winningLotto
    }

    private fun getWinningLottoNumbers(): Lotto =
        try {
            outputView.printWinningLottoNumbersOfLastWeekGuide()
            inputView.readWinningLottoNumbersOfLastWeek()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getWinningLottoNumbers()
        }

    private fun getWinningLotto(winningLottoNumbers: Lotto): WinningLotto =
        try {
            val bonusNumber = getBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getWinningLotto(winningLottoNumbers)
        }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        return inputView.readBonusNumber()
    }

    private fun processLottoStatistics(lottoStatistics: LottoStatistics) {
        outputView.printLottoStatistics(lottoStatistics.rankStatistics)
        outputView.printLottoRateOfReturn(lottoStatistics.rateOfReturn, lottoStatistics.isLossMoney())
    }
}
