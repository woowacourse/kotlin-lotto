package controller

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.LottoResult
import domain.model.ManualLottoAmount
import domain.model.ManualLottoAmount.Companion.toManualLottoAmountResult
import domain.model.ManualLottoAmountResult
import domain.model.PurchasePrice
import domain.model.PurchasePrice.Companion.toAmount
import domain.model.PurchasePrice.Companion.toPurchasePriceResult
import domain.model.PurchasePriceResult
import domain.model.WinningLotto
import domain.service.AutoLottoGenerator
import domain.service.LottoMatchCalculator
import domain.service.ManualLottoGenerator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoMatchCalculator: LottoMatchCalculator,
) {
    fun run() {
        val purchasePrice: PurchasePrice = getPurchasePrice()
        val manualLottoAmount: ManualLottoAmount = getManualLottoAmount(purchasePrice)
        val manualLotto: List<Lotto> = purchaseManualLotto(manualLottoAmount)
        val autoLotto: List<Lotto> = purchaseAutoLotto(purchasePrice.toAmount() - manualLottoAmount.value)
        displayLottoTicket(manualLotto, autoLotto)

        val winningNumbers: Lotto = getWinningNumbers()
        val winningLotto: WinningLotto = getWinningLotto(winningNumbers)
        val lottoResult: LottoResult = lottoMatchCalculator.calculate(manualLotto + autoLotto, winningLotto)
        val profitRate: String = lottoResult.getProfitRate(purchasePrice.value)
        displayResult(lottoResult, profitRate)
    }

    private fun getPurchasePrice(): PurchasePrice {
        val input: Int = inputView.readPurchasePrice()
        return when (val result = input.toPurchasePriceResult()) {
            is PurchasePriceResult.Success -> result.purchasePrice
            is PurchasePriceResult.InvalidMinimumPurchaseAmount -> {
                outputView.printExceptionMessage(result.errorMsg)
                getPurchasePrice()
            }

            is PurchasePriceResult.InvalidThousandWonUnit -> {
                outputView.printExceptionMessage(result.errorMsg)
                getPurchasePrice()
            }
        }
    }

    private fun getManualLottoAmount(purchasePrice: PurchasePrice): ManualLottoAmount {
        val input = inputView.readManualLottoAmount()
        return when (val result = input.toManualLottoAmountResult(purchasePrice.toAmount())) {
            is ManualLottoAmountResult.CannotMoreThanTotalPurchaseAmount -> {
                outputView.printExceptionMessage(result.errorMsg)
                getManualLottoAmount(purchasePrice)
            }

            is ManualLottoAmountResult.Success -> result.amount
        }
    }

    private fun purchaseManualLotto(manualLottoAmount: ManualLottoAmount): List<Lotto> {
        inputView.printReadManualLottoNumbers()
        val inputManualLottoNumbers: List<String> = List(manualLottoAmount.value) { inputView.readManualLottoNumbers() }
        return runCatching {
            ManualLottoGenerator(inputManualLottoNumbers).makeLotto(manualLottoAmount.value)
        }.getOrElse {
            outputView.printExceptionMessage(it.message ?: DEFAULT_EXCEPTION_MSG)
            purchaseManualLotto(manualLottoAmount)
        }
    }

    private fun purchaseAutoLotto(amount: Int): List<Lotto> = AutoLottoGenerator().makeLotto(amount)

    private fun displayLottoTicket(
        manualLotto: List<Lotto>,
        autoLotto: List<Lotto>,
    ) {
        outputView.printPurchasedLottoCount(manualLotto.size, autoLotto.size)
        outputView.printPurchasedLotto(manualLotto + autoLotto)
    }

    private fun getWinningNumbers(): Lotto {
        val input: String = inputView.readWinningNumbers()
        return runCatching {
            Lotto(input.split(",").map { LottoNumber.from(it.toInt()) }.toSet())
        }.getOrElse {
            outputView.printExceptionMessage(it.message ?: DEFAULT_EXCEPTION_MSG)
            getWinningNumbers()
        }
    }

    private fun getWinningLotto(winningNumbers: Lotto): WinningLotto {
        val input = inputView.readBonusNumber()
        return runCatching {
            val bonusNumber = LottoNumber.from(input.toInt())
            WinningLotto(winningNumbers, bonusNumber)
        }.getOrElse {
            outputView.printExceptionMessage(it.message ?: DEFAULT_EXCEPTION_MSG)
            getWinningLotto(winningNumbers)
        }
    }

    private fun displayResult(
        lottoResult: LottoResult,
        profitRate: String,
    ) {
        outputView.printWinningResult(lottoResult, profitRate)
        if (profitRate.toDouble() < 1) outputView.printLossMessage()
    }

    companion object {
        const val DEFAULT_EXCEPTION_MSG = "예외가 발생했습니다."
    }
}
