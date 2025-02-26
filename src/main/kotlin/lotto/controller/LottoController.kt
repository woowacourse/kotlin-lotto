package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseMoney = getPurchaseMoney()
        val wholeLottoCount = getWholeLottoCount(purchaseMoney)

        val manualLottoCount = getPurchaseManualLottoCount(lottoCount)

        outputView.printLottoCount(lottoCount)
        val lottos = lottoMachine.createLottos(lottoCount)
        outputView.printLottos(lottos)

        val winningLotto = getWinningLotto()
        val lottoStatistics: LottoStatistics = winningLotto.calculateStatistics(lottos, purchaseMoney)

        processLottoStatistics(lottoStatistics)
    }

    private fun getPurchaseManualLottoCount(wholeLottoCount: LottoCount): LottoCount =
        try {
            outputView.printPurchaseManualLottoCountGuide()
            val manualLottoCount = inputView.readManualLottoPurchaseCount()
            wholeLottoCount.validateLottoCount(manualLottoCount)
            manualLottoCount
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getPurchaseManualLottoCount(wholeLottoCount)
        }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        try {
            outputView.printPurchaseAmountGuide()
            inputView.readLottoPurchaseAmount()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getPurchaseMoney()
        }

    private fun getWholeLottoCount(purchaseMoney: LottoPurchaseAmount): LottoCount {
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
