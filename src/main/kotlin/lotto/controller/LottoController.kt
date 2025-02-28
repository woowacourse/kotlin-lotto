package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoResult
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
        val purchaseMoney: LottoPurchaseAmount = getPurchaseMoney()
        val lottos: Lottos = createLottos(purchaseMoney)
        outputView.printLottos(lottos)

        val winningLotto: WinningLotto = getWinningLotto()
        val lottoResult: LottoResult = lottos.calculateLottoResult(winningLotto)
        displayLottoResult(lottoResult, purchaseMoney)
    }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        try {
            outputView.printPurchaseAmountGuide()
            inputView.readLottoPurchaseAmount()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getPurchaseMoney()
        }

    private fun createLottos(purchaseMoney: LottoPurchaseAmount): Lottos {
        val lottoCount: LottoCount = getLottoCount(purchaseMoney)
        val manualLottoCount: LottoCount = getManualLottoCount()
        val autoLottoCount: LottoCount = lottoCount.subtract(manualLottoCount)
        val lottos: MutableList<Lotto> = mutableListOf()

        createManualLottos(manualLottoCount, lottos)
        createAutoLottos(autoLottoCount, lottos)
        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        return Lottos(lottos)
    }

    private fun createManualLottos(
        manualLottoCount: LottoCount,
        lottos: MutableList<Lotto>,
    ) {
        outputView.printManualLottoNumbersGuide()
        repeat(manualLottoCount.count) {
            val manualLottoNumbers: List<LottoNumber> = inputView.readLottoNumbers()
            val manualLotto: Lotto = lottoMachine.createManualLotto(manualLottoNumbers)
            lottos.add(manualLotto)
        }
    }

    private fun createAutoLottos(
        autoLottoCount: LottoCount,
        lottos: MutableList<Lotto>,
    ) {
        repeat(autoLottoCount.count) {
            val autoLotto: Lotto = lottoMachine.createAutoLotto()
            lottos.add(autoLotto)
        }
    }

    private fun getManualLottoCount(): LottoCount =
        try {
            outputView.printManualLottoCountGuide()
            inputView.readManualLottoCount()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getManualLottoCount()
        }

    private fun getLottoCount(purchaseMoney: LottoPurchaseAmount): LottoCount {
        val lottoCount = LottoCount(purchaseMoney.getLottoCount())
        return lottoCount
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers: Lotto = getWinningLottoNumbers()
        val winningLotto: WinningLotto = createWinningLotto(winningLottoNumbers)
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
            val bonusNumber: LottoNumber = getBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            createWinningLotto(winningLottoNumbers)
        }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        return inputView.readBonusNumber()
    }

    private fun displayLottoResult(
        lottoResult: LottoResult,
        purchasedAmount: LottoPurchaseAmount,
    ) {
        outputView.printLottoResult(lottoResult)
        val rateOfReturn: Double = lottoResult.getRateOfReturn(purchasedAmount.money)
        outputView.printLottoRateOfReturn(rateOfReturn, lottoResult.getIsLossMoney(rateOfReturn))
    }
}
