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
        val lottos: Lottos = getLottos(purchaseMoney)
        val winningLotto: WinningLotto = getWinningLotto()
        val lottoResult: LottoResult = lottos.calculateLottoResult(winningLotto)
        displayLottoResult(lottoResult, purchaseMoney)
    }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        runCatching {
            outputView.printPurchaseAmountGuide()
            inputView.readLottoPurchaseAmount()
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            getPurchaseMoney()
        }

    private fun getLottos(purchaseMoney: LottoPurchaseAmount): Lottos {
        val (manualLottoCount: LottoCount, autoLottoCount: LottoCount) = calculateLottoCounts(purchaseMoney)
        val lottos: Lottos = createLottos(manualLottoCount, autoLottoCount)

        displayLottoInfo(manualLottoCount, autoLottoCount, lottos)
        return lottos
    }

    private fun calculateLottoCounts(purchaseMoney: LottoPurchaseAmount): Pair<LottoCount, LottoCount> {
        val totalLottoCount: LottoCount = getLottoCount(purchaseMoney)
        val manualLottoCount: LottoCount = getManualLottoCount(totalLottoCount)
        val autoLottoCount: LottoCount = getAutoLottoCount(totalLottoCount, manualLottoCount)
        return manualLottoCount to autoLottoCount
    }

    private fun getLottoCount(purchaseMoney: LottoPurchaseAmount): LottoCount = LottoCount(purchaseMoney.getLottoCount())

    private fun getManualLottoCount(totalLottoCount: LottoCount): LottoCount =
        runCatching {
            outputView.printManualLottoCountGuide()
            val manualLottoCount: LottoCount = inputView.readManualLottoCount()
            manualLottoCount.validateLottoMaxCount(totalLottoCount)
            manualLottoCount
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            getManualLottoCount(totalLottoCount)
        }

    private fun getAutoLottoCount(
        totalLottoCount: LottoCount,
        manualLottoCount: LottoCount,
    ) = totalLottoCount.subtract(manualLottoCount)

    private fun createLottos(
        manualLottoCount: LottoCount,
        autoLottoCount: LottoCount,
    ): Lottos {
        val manualLottos = getManualLottos(manualLottoCount)
        val autoLottos = getAutoLottos(autoLottoCount)
        return Lottos(manualLottos, autoLottos)
    }

    private fun getManualLottos(manualLottoCount: LottoCount): List<Lotto> {
        outputView.printManualLottoNumbersGuide()
        return List(manualLottoCount.count) { getManualLotto() }
    }

    private fun getManualLotto(): Lotto =
        runCatching {
            val lottoNumbers: List<LottoNumber> = inputView.readLottoNumbers()
            lottoMachine.createManualLotto(lottoNumbers)
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            getManualLotto()
        }

    private fun getAutoLottos(autoLottoCount: LottoCount) = List(autoLottoCount.count) { getAutoLotto() }

    private fun getAutoLotto(): Lotto = lottoMachine.createAutoLotto()

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbers: Lotto = getWinningLottoNumbers()
        val winningLotto: WinningLotto = createWinningLotto(winningLottoNumbers)
        return winningLotto
    }

    private fun displayLottoInfo(
        manualLottoCount: LottoCount,
        autoLottoCount: LottoCount,
        lottos: Lottos,
    ) {
        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        outputView.printLottos(lottos)
    }

    private fun getWinningLottoNumbers(): Lotto =
        runCatching {
            outputView.printWinningLottoNumbersOfLastWeekGuide()
            inputView.readWinningLottoNumbersOfLastWeek()
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            getWinningLottoNumbers()
        }

    private fun createWinningLotto(winningLottoNumbers: Lotto): WinningLotto =
        runCatching {
            val bonusNumber: LottoNumber = getBonusNumber()
            WinningLotto(winningLottoNumbers, bonusNumber)
        }.getOrElse { error ->
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
