package controller

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.PurchasePrice
import domain.model.Rank
import domain.model.WinningLotto
import domain.service.LottoGenerator
import util.Messenger.getWinningMessage
import util.Messenger.makePurchaseLottoMessage
import util.retryWhenException
import validator.NumericValidator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoGenerator: LottoGenerator,
) {
    fun run() {
        val purchasePrice = getPurchasePrice()
        val purchaseLotto: List<Lotto> = buyLotto(purchasePrice)
        displayBuyLotto(purchaseLotto)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto = getWinningLotto(winningNumbers)

        val winningResult = winningLotto.calculate(purchaseLotto)
        val profitRate = winningLotto.getProfitRate(purchasePrice, winningResult)
        displayResult(winningResult, profitRate)
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

    private fun buyLotto(money: PurchasePrice): List<Lotto> {
        return lottoGenerator.generate(money)
    }

    private fun displayBuyLotto(lotto: List<Lotto>) {
        outputView.printPurchasedLottoCount(lotto.size)
        outputView.printPurchasedLotto(makePurchaseLottoMessage(lotto))
    }

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(input.split(",").map { LottoNumber(it.trim().toInt()) })
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
        lottoResult: Map<Rank, Int>,
        profitRate: String,
    ) {
        outputView.printWinningResult(getWinningMessage(lottoResult), profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }
}
