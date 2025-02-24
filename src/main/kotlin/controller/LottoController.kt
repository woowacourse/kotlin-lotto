package controller

import domain.model.Lotto
import domain.model.LottoMatchResult
import domain.model.LottoNumber
import domain.model.LottoOrderRequest
import domain.model.PassivityLottoAmount
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.LottoGenerator
import util.retryWhenException
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoGenerator: LottoGenerator,
) {
    fun run() {
        val order = buyLotto()
        val pickedLotto = pickLotto(order.quickPickLottoAmount)
        displayPickedLotto(passivityLottoAmount = order.amount.value, lotto = pickedLotto)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto = getWinningLotto(winningNumbers)

        val combinedLotto = order.combine(pickedLotto)

        val winningResult = winningLotto.calculate(combinedLotto)
        val profitRate = winningResult.getProfitRate(order.money)
        displayResult(winningResult, profitRate)
    }

    private fun buyLotto(): LottoOrderRequest {
        val purchasePrice = getPurchasePrice()
        val passiveLottoAmount = getPassiveLottoAmount(purchasePrice)
        val passiveLottoNumbers = getPassiveLottoNumbers(passiveLottoAmount)
        return LottoOrderRequest(purchasePrice, passiveLottoAmount, passiveLottoNumbers)
    }

    private fun getPurchasePrice(): PurchasePrice =
        retryWhenException(
            action = {
                val input = inputView.readPurchasePrice()
                PurchasePrice(input)
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getPassiveLottoAmount(money: PurchasePrice): PassivityLottoAmount =
        retryWhenException(
            action = {
                val passive = inputView.readPassivityLottoAmount()
                PassivityLottoAmount.create(passive, money)
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getPassiveLottoNumbers(amount: PassivityLottoAmount): List<Lotto> =
        retryWhenException(
            action = {
                outputView.printPassiveLottoRequest()
                List(amount.value) {
                    val input = inputView.readPassivityLottoNumbers()
                    Lotto(input.map { LottoNumber(it) })
                }
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun pickLotto(quickPickLottoAmount: Int): List<Lotto> {
        return lottoGenerator.generate(quickPickLottoAmount)
    }

    private fun displayPickedLotto(
        passivityLottoAmount: Int,
        lotto: List<Lotto>,
    ) {
        outputView.printPurchasedLottoAmount(passivityLottoAmount, lotto.size)
        outputView.printPurchasedLotto(lotto)
    }

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(input.map { LottoNumber(it) })
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getWinningLotto(winningNumbers: Lotto): WinningLotto =
        retryWhenException(
            action = {
                val input = inputView.readBonusNumber()
                WinningLotto(winningNumbers, LottoNumber(input))
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun displayResult(
        lottoResult: LottoMatchResult,
        profitRate: Double,
    ) {
        outputView.printWinningResult(lottoResult, profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }
}
