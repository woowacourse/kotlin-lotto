package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.Lottos
import lotto.model.WinningLotto
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
        val lottos = Lottos(List(lottoCount) { lottoMachine.createLotto() })
        outputView.printLottos(lottos)

        val winningLotto = getWinningLotto()
        val lottoStatistics = LottoStatistics(lottos, winningLotto, purchaseMoney)

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
        outputView.printLottoCount(lottoCount)
        return lottoCount
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers = getWinningLottoNumbers()
        val winningLotto = createWinningLotto(winningLottoNumbers)
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

    private fun createWinningLotto(winningLottoNumbers: Lotto): WinningLotto =
        try {
            val bonusNumber = getBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            createWinningLotto(winningLottoNumbers)
        }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        return inputView.readBonusNumber()
    }

    private fun processLottoStatistics(lottoStatistics: LottoStatistics) {
        outputView.printLottoStatistics(lottoStatistics.lottoStatistics)
        val rateOfReturn = lottoStatistics.getRateOfReturn()
        outputView.printLottoRateOfReturn(rateOfReturn, lottoStatistics.getIsLossMoney(rateOfReturn))
    }
}
