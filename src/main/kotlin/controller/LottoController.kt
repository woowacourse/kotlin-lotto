package controller

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.LottoResult
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.LottoGenerator
import domain.service.LottoMatchCalculator
import util.LottoFactory
import util.retryWhenException
import validator.ManualLottoAmountValidator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice: PurchasePrice = getPurchasePrice()
        val lotto: List<Lotto> = buyLotto(purchasePrice, getManualLottoAmount(purchasePrice))
        displayBuyLotto(lotto)
        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)
        val lottoResult: LottoResult = LottoMatchCalculator(lotto, winningLotto).calculate()
        val profitRate = lottoResult.getProfitRate(purchasePrice)

        displayResult(lottoResult, profitRate)
    }

    private fun getPurchasePrice(): PurchasePrice =
        retryWhenException(
            action = {
                val input = inputView.readPurchasePrice()
                PurchasePrice(input.toInt())
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getManualLottoAmount(purchasePrice: PurchasePrice): Int {
        return retryWhenException(
            action = {
                val manualLottoAmount = inputView.readManualLottoAmount().toInt()
                LottoGenerator(purchasePrice).getAutoLottoAmount(manualLottoAmount).also { ManualLottoAmountValidator(it) }
                manualLottoAmount
            },
            onError = { outputView.printErrorMessage(it) },
        )
    }

    private fun buyLotto(
        money: PurchasePrice,
        manualLottoAmount: Int,
    ): List<Lotto> {
        return retryWhenException(
            action = {
                inputView.askForManualLottoNumber()
                val manualLottoNumbers = List(manualLottoAmount) { inputView.readManualLottoNumber() }
                val generator = LottoGenerator(money)
                val lotto = generator.makeLotto(manualLottoAmount, manualLottoNumbers)
                lotto
            },
            onError = { outputView.printErrorMessage(it) },
        )
    }

    private fun displayBuyLotto(lotto: List<Lotto>) {
        outputView.printPurchasedLottoCount(lotto.size)
        outputView.printPurchasedLotto(lotto.map { LottoFactory.extractionNumber(it).sorted() }.joinToString("\n"))
    }

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(input.map { LottoNumber(it.toInt()) })
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getWinningLotto(winningNumbers: Lotto): WinningLotto =
        retryWhenException(
            action = {
                val input = inputView.readBonusNumber()
                val bonusNumber = BonusNumber(input.toInt())
                WinningLotto(winningNumbers, bonusNumber)
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun displayResult(
        lottoResult: LottoResult,
        profitRate: String,
    ) {
        outputView.printResultTitle()
        outputView.printWinningResult(lottoResult, profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }
}
