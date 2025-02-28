package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumbersGenerator
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoResult
import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoNumbersGenerator: LottoNumbersGenerator,
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
        val lottoCount: Int = getLottoCount(purchaseMoney)
        val manualLottoCount: Int = getManualLottoCount()
        require(lottoCount >= manualLottoCount) { "수동으로 입력할 로또 개수는 최대 $lottoCount 까지 가능합니다." }
        val lottos: MutableList<Lotto> = mutableListOf()
        val autoLottoCount: Int = lottoCount - manualLottoCount
        outputView.printManualLottoNumbersGuide()
        repeat(manualLottoCount) {
            val manualLotto: Lotto = inputView.readLottoNumbers()
            lottos.add(manualLotto)
        }
        repeat(autoLottoCount) {
            val lottoNumbers: List<LottoNumber> = lottoNumbersGenerator.generateLottoNumbers()
            lottos.add(Lotto.create(lottoNumbers))
        }
        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        return Lottos(lottos)
    }

    private fun getManualLottoCount(): Int =
        try {
            outputView.printManualLottoCountGuide()
            inputView.readManualLottoCount()
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message)
            getManualLottoCount()
        }

    private fun getLottoCount(purchaseMoney: LottoPurchaseAmount): Int {
        val lottoCount: Int = purchaseMoney.getLottoCount()
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
            inputView.readLottoNumbers()
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
