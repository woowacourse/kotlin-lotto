package controller

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.LottoResult
import domain.model.PurchaseLotto
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.LottoGenerator
import domain.service.LottoMatchCalculator
import lotto.util.retryWhenException
import validator.NumericValidator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice: PurchasePrice = getPurchasePrice()
        val lotto: PurchaseLotto = buyLotto(purchasePrice)
        displayBuyLotto(lotto)
        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)
        val lottoResult: LottoResult = LottoMatchCalculator(lotto, winningLotto).calculate()
        val profitRate = lottoResult.getProfitRate(purchasePrice)

        displayResult(lottoResult, profitRate)
    }

    fun getPurchasePrice(): PurchasePrice =
        retryWhenException(
            action = {
                val input = inputView.readPurchasePrice()
                NumericValidator(input)
                PurchasePrice(input.toInt())
            },
            onError = { outputView.printErrorMessage(it) },
        )

    fun buyLotto(money: PurchasePrice): PurchaseLotto {
        val generator = LottoGenerator(money)
        val lotto = generator.makeLotto()
        return lotto
    }

    fun displayBuyLotto(lotto: PurchaseLotto) {
        outputView.printPurchasedLottoCount(lotto.values.size)
        outputView.printPurchasedLotto(lotto.toString())
    }

    fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(input.split(",").map { it.trim().toInt() })
            },
            onError = { outputView.printErrorMessage(it) },
        )

    fun getWinningLotto(winningNumbers: Lotto): WinningLotto =
        retryWhenException(
            action = {
                val input = inputView.readBonusNumber()
                NumericValidator(input)
                val bonusNumber = BonusNumber(input.toInt())
                WinningLotto(winningNumbers, bonusNumber)
            },
            onError = { outputView.printErrorMessage(it) },
        )

    fun displayResult(
        lottoResult: LottoResult,
        profitRate: String,
    ) {
        outputView.printWinningResult(lottoResult, profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }
}
