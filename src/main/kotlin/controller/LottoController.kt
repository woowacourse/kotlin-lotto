package controller

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.LottoResult
import domain.model.ManualLottoAmount
import domain.model.PurchasePrice
import domain.model.PurchasePrice.Companion.toAmount
import domain.model.WinningLotto
import domain.service.AutoLottoGenerator
import domain.service.LottoMatchCalculator
import domain.service.ManualLottoGenerator
import util.retryWhenException
import validator.NumericValidator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val autoLottoGenerator: AutoLottoGenerator,
    private val manualLottoGenerator: ManualLottoGenerator,
    private val lottoMatchCalculator: LottoMatchCalculator,
) {
    fun run() {
        val purchasePrice: PurchasePrice = getPurchasePrice()
        val manualLottoAmount: ManualLottoAmount = getManualLottoAmount(purchasePrice)
        val manualLotto: List<Lotto> = purchaseManualLotto(manualLottoAmount)
        val autoLotto: List<Lotto> = purchaseAutoLotto(purchasePrice.toAmount(), manualLottoAmount)
        displayLottoTicket(manualLotto, autoLotto)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)
        val lottoResult: LottoResult = lottoMatchCalculator.calculate(manualLotto + autoLotto, winningLotto)
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

    private fun getManualLottoAmount(purchasePrice: PurchasePrice): ManualLottoAmount =
        retryWhenException(
            action = {
                val input = inputView.readManualLottoAmount()
                NumericValidator(input)
                ManualLottoAmount(input.toInt(), purchasePrice.toAmount())
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun purchaseManualLotto(manualLottoAmount: ManualLottoAmount): List<Lotto> {
        inputView.printReadManualLottoNumbers()
        return List(manualLottoAmount.value) { getManualLotto() }
    }

    private fun getManualLotto(): Lotto =
        retryWhenException(
            action = {
                val manualLottoNumbers = getManualLottoNumbers()
                manualLottoGenerator.setManualLottoNumbers(manualLottoNumbers)
                manualLottoGenerator.makeLotto()
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun getManualLottoNumbers(): Set<Int> =
        retryWhenException(
            action = {
                val input = inputView.readManualLottoNumbers()
                input
                    .split(",")
                    .map {
                        NumericValidator(it)
                        it.toInt()
                    }.toSet()
            },
            onError = { outputView.printErrorMessage(it) },
        )

    private fun purchaseAutoLotto(
        totalAmount: Int,
        manualLottoAmount: ManualLottoAmount,
    ): List<Lotto> =
        List(totalAmount - manualLottoAmount.value) {
            autoLottoGenerator.makeLotto()
        }

    private fun displayLottoTicket(
        manualLotto: List<Lotto>,
        autoLotto: List<Lotto>,
    ) {
        outputView.printPurchasedLottoCount(manualLotto.size, autoLotto.size)
        outputView.printPurchasedLotto(manualLotto + autoLotto)
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
                            LottoNumber.from(it.trim().toInt())
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
                val bonusNumber = LottoNumber.from(input.toInt())
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
