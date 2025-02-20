package controller

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.LottoResult
import domain.model.PurchaseLotto
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.LottoGenerator
import domain.service.LottoMatchCalculator
import util.retryWhenException
import validator.NumericValidator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice = getPurchasePrice()
        val lotto: PurchaseLotto = buyLotto(purchasePrice)
        displayBuyLotto(lotto)
        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)

        val lottoResult = winningLotto.calculate(purchaseLotto)
        val profitRate = lottoResult.getProfitRate(purchasePrice)
        displayResult(lottoResult, profitRate)
    }

    private fun getPurchasePrice(): PurchasePrice =
        retryWhenException(
            action = {
                val input = inputView.readPurchasePrice()
                NumericValidator(input)
                PurchasePrice(input.toInt())
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun buyLotto(money: PurchasePrice): PurchaseLotto {
        val generator = LottoGenerator(money)
        val lotto = generator.makeLotto()
        return lotto
    }

    private fun displayBuyLotto(lotto: PurchaseLotto) {
        outputView.printPurchasedLottoCount(lotto.values.size)
        outputView.printPurchasedLotto(lotto.toString())
    }

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(input.split(",").map { it.trim().toInt() })
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getWinningLotto(winningNumbers: Lotto): WinningLotto =
        retryWhenException(
            action = {
                val input = inputView.readBonusNumber()
                NumericValidator(input)
                WinningLotto(winningNumbers, input.toInt())
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun displayResult(
        lottoResult: LottoResult,
        profitRate: String,
    ) {
        outputView.printWinningResult(lottoResult.toString(), profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }
}
