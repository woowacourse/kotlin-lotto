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

        val winningLotto = getWinningLotto(purchaseMoney)
        val lottoStatistics: LottoStatistics = winningLotto.calculateStatistics(lottos)

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

    private fun getWinningLotto(purchaseMoney: LottoPurchaseAmount): WinningLotto {
        val winningLottoNumbers = getWinningLottoNumbers()
        val winningLotto = getWinningLotto(winningLottoNumbers, purchaseMoney)
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

    private fun getWinningLotto(
        winningLottoNumbers: Lotto,
        purchaseMoney: LottoPurchaseAmount,
    ): WinningLotto =
        try {
            val bonusNumber = getBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber, purchaseMoney)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getWinningLotto(winningLottoNumbers, purchaseMoney)
        }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        return inputView.readBonusNumber()
    }

    private fun processLottoStatistics(lottoStatistics: LottoStatistics) {
        outputView.printLottoStatistics(lottoStatistics.rankStatistics)
        val rateOfReturn = lottoStatistics.getRateOfReturn()
        outputView.printLottoRateOfReturn(rateOfReturn, lottoStatistics.getIsLossMoney(rateOfReturn))
    }
}
