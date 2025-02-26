package controller

import domain.model.Lotto
import domain.model.LottoMatchResult
import domain.model.LottoNumber
import domain.model.LottoOrderRequest
import domain.model.ManualLottoAmount
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.AutoLottoMachine
import domain.service.ManualLottoMachine
import util.retryWhenException
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val order: LottoOrderRequest = getOrder()
        val autoLotto = buyAutoLotto(order.autoLottoAmount)
        val allLotto = order.combine(autoLotto)
        displayPickedLotto(manualLottoAmount = order.amount.value, lotto = allLotto)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto = getWinningLotto(winningNumbers)

        val winningResult = winningLotto.calculate(allLotto)
        val profitRate = winningResult.getProfitRate(order.money)
        displayResult(winningResult, profitRate)
    }

    private fun getOrder(): LottoOrderRequest {
        val purchasePrice = getPurchasePrice()
        val manualLottoAmount = getManualLottoAmount(purchasePrice)
        val manualLotto = getManualLottoNumbers(manualLottoAmount)
        return LottoOrderRequest(
            money = purchasePrice,
            amount = manualLottoAmount,
            manualLotto = manualLotto,
        )
    }

    private fun getPurchasePrice(): PurchasePrice {
        return retryWhenException(
            action = { PurchasePrice(inputView.readPurchasePrice()) },
            onError = { outputView.printErrorMessage(it) },
        )
    }

    private fun getManualLottoAmount(money: PurchasePrice): ManualLottoAmount =
        retryWhenException(
            action = {
                val input = inputView.readManualLottoAmount()
                ManualLottoAmount(input, money)
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getManualLottoNumbers(amount: ManualLottoAmount): List<Lotto> =
        retryWhenException(
            action = {
                outputView.printManualLottoRequest()
                List(amount.value) {
                    val input = inputView.readManualLottoNumbers()
                    ManualLottoMachine(input).generate()
                }
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun displayPickedLotto(
        manualLottoAmount: Int,
        lotto: List<Lotto>,
    ) {
        outputView.printPurchasedLottoAmount(manualLottoAmount, lotto.size)
        outputView.printPurchasedLotto(lotto)
    }

    private fun buyAutoLotto(autoLottoAmount: Int): List<Lotto> {
        return List(autoLottoAmount) {
            AutoLottoMachine.generate()
        }
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
