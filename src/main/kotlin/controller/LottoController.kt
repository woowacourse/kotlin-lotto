package controller

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.LottoResult
import domain.model.LottoTicket
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
        val purchasePrice: PurchasePrice = getPurchasePrice()
        val lottoTicket: LottoTicket = purchaseLotto(purchasePrice)
        displayLottoTicket(lottoTicket)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)
        val lottoResult: LottoResult = LottoMatchCalculator().calculate(lottoTicket, winningLotto)
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

    private fun purchaseLotto(money: PurchasePrice): LottoTicket {
        val generator = LottoGenerator(money)
        val lotto = generator.makeLotto()
        return lotto
    }

    private fun displayLottoTicket(lotto: LottoTicket) {
        outputView.printPurchasedLottoCount(lotto.values.size)
        outputView.printPurchasedLotto(lotto.toString())
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
                val bonusNumber = LottoNumber(input.toInt())
                WinningLotto(winningNumbers, bonusNumber)
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun displayResult(
        lottoResult: LottoResult,
        profitRate: String,
    ) {
        outputView.printWinningResult(lottoResult, profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }
}
