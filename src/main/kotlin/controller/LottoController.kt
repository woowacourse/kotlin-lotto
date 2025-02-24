package controller

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.LottoResult
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.AutoLottoGenerator
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
        val purchaseLottos: List<Lotto> = purchaseLotto(purchasePrice)
        displayLottoTicket(purchaseLottos)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)
        val lottoResult: LottoResult = LottoMatchCalculator().calculate(purchaseLottos, winningLotto)
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

    private fun purchaseLotto(money: PurchasePrice): List<Lotto> {
        val lottoGenerator = AutoLottoGenerator()
        val amount = money.value / PurchasePrice.Companion.STANDARD_AMOUNT_UNIT
        return List(amount) { lottoGenerator.makeLotto() }
    }

    private fun displayLottoTicket(lottos: List<Lotto>) {
        outputView.printPurchasedLottoCount(lottos.size)
        outputView.printPurchasedLotto(lottos)
    }

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(
                    input
                        .split(",")
                        .map {
                            NumericValidator(it)
                            LottoNumber(it.trim().toInt())
                        }.toSet(),
                )
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
