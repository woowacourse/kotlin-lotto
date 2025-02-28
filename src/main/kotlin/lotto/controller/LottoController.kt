package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.model.lottomachine.AutomaticLottoMachine
import lotto.model.lottomachine.ManualLottoMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseMoney = getPurchaseMoney()
        val wholeLottoCount = getWholeLottoCount(purchaseMoney)
        val lottos = getLottos(wholeLottoCount)
        outputView.printLottoCount(lottos.getManualLottoCount(), lottos.getAutomaticLottoCount())
        outputView.printLottos(lottos)
        val winningLotto = getWinningLotto()
        val lottoStatistics: LottoStatistics = winningLotto.calculateStatistics(lottos, purchaseMoney)
        processLottoStatistics(lottoStatistics)
    }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        try {
            inputView.readLottoPurchaseAmount()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getPurchaseMoney()
        }

    private fun getWholeLottoCount(purchaseMoney: LottoPurchaseAmount): LottoCount {
        val lottoCount = purchaseMoney.getLottoCount()
        return lottoCount
    }

    private fun getLottos(wholeLottoCount: LottoCount): Lottos {
        val manualLottoCount = getPurchaseManualLottoCount(wholeLottoCount)
        val manualLottoBundle = getManualLottoBundle(manualLottoCount)
        val automaticLottoCount = wholeLottoCount.minus(manualLottoCount)
        val automaticLottoBundle = getAutomaticLottoBundle(automaticLottoCount)
        return Lottos(manualLottoBundle, automaticLottoBundle)
    }

    private fun getPurchaseManualLottoCount(wholeLottoCount: LottoCount): LottoCount =
        try {
            val manualLottoCount = inputView.readManualLottoPurchaseCount()
            if (!wholeLottoCount.isPurchasableLottoCount(manualLottoCount)) {
                getPurchaseManualLottoCount(wholeLottoCount)
            } else {
                manualLottoCount
            }
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getPurchaseManualLottoCount(wholeLottoCount)
        }

    private fun getManualLottoBundle(manualLottoCount: LottoCount): List<Lotto> {
        if (manualLottoCount.number != 0) outputView.printManualLottoNumbersGuide()
        return List(manualLottoCount.number) { getManualLotto() }
    }

    private fun getManualLotto(): Lotto =
        try {
            val manualLottoNumbers = inputView.readManualLottoNumbers()
            ManualLottoMachine.createLotto(manualLottoNumbers)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getManualLotto()
        }

    private fun getAutomaticLottoBundle(automaticLottoCount: LottoCount): List<Lotto> =
        try {
            AutomaticLottoMachine.createLottoBundle(automaticLottoCount)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getAutomaticLottoBundle(automaticLottoCount)
        }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers = inputView.readWinningLottoNumbersOfLastWeek()
        val winningLotto = getWinningLotto(winningLottoNumbers)
        return winningLotto
    }

    private fun getWinningLotto(winningLottoNumbers: Lotto): WinningLotto =
        try {
            val bonusNumber = inputView.readBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getWinningLotto(winningLottoNumbers)
        }

    private fun processLottoStatistics(lottoStatistics: LottoStatistics) {
        outputView.printLottoStatistics(lottoStatistics.rankStatistics)
        outputView.printLottoRateOfReturn(lottoStatistics.rateOfReturn, lottoStatistics.isLossMoney())
    }
}
