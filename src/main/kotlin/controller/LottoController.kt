package controller

import domain.model.LottoMatchResult
import domain.model.LottoOrderRequest
import domain.model.lotto.Lotto
import domain.model.machine.AutoLottoMachine
import domain.model.machine.ManualLottoMachine
import domain.model.manual.ManualLottoAmount
import domain.model.number.LottoNumber
import domain.model.price.PurchasePrice
import domain.model.winning.WinningLotto
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val order: LottoOrderRequest = getOrder()
        val autoLotto = buyAutoLotto(order.autoLottoAmount)
        val manualLotto = makeManualLotto(order.manualLotto)

        val allLotto = autoLotto + manualLotto
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
        return runCatching {
            PurchasePrice(inputView.readPurchasePrice())
        }.getOrElse { e ->
            println(e.message)
            getPurchasePrice()
        }
    }

    private fun getManualLottoAmount(money: PurchasePrice): ManualLottoAmount {
        return runCatching {
            val input = inputView.readManualLottoAmount()
            ManualLottoAmount(input, money)
        }.getOrElse { e ->
            outputView.printErrorMessage(e.message)
            getManualLottoAmount(money)
        }
    }

    private fun getManualLottoNumbers(amount: ManualLottoAmount): List<List<Int>> {
        return runCatching {
            outputView.printManualLottoRequest()
            List(amount.value) {
                inputView.readManualLottoNumbers()
            }
        }.getOrElse {
            outputView.printErrorMessage(it.message)
            getManualLottoNumbers(amount)
        }
    }

    private fun displayPickedLotto(
        manualLottoAmount: Int,
        lotto: List<Lotto>,
    ) {
        outputView.printPurchasedLottoAmount(manualLottoAmount, lotto.size)
        outputView.printPurchasedLotto(lotto)
    }

    private fun makeManualLotto(manualLotto: List<List<Int>>): List<Lotto> {
        return List(manualLotto.size) { idx ->
            buyManualLotto(manualLotto[idx], idx)
        }
    }

    private fun buyManualLotto(
        lotto: List<Int>,
        index: Int,
    ): Lotto {
        return runCatching {
            ManualLottoMachine(lotto).generate()
        }.getOrElse {
            val newManualLotto = getOneManualLottoNumbers(it.message, index)
            buyManualLotto(newManualLotto, index)
        }
    }

    private fun getOneManualLottoNumbers(
        message: String?,
        index: Int,
    ): List<Int> {
        return runCatching {
            outputView.printRetryManualLottoNumber(message, index + 1)
            inputView.readManualLottoNumbers()
        }.getOrElse {
            getOneManualLottoNumbers(message, index)
        }
    }

    private fun buyAutoLotto(autoLottoAmount: Int): List<Lotto> {
        return List(autoLottoAmount) {
            AutoLottoMachine.generate()
        }
    }

    private fun getWinningNumbers(): Lotto {
        return runCatching {
            val input = inputView.readWinningNumbers()
            Lotto(input.map { LottoNumber.from(it) })
        }.getOrElse { e ->
            outputView.printErrorMessage(e.message)
            getWinningNumbers()
        }
    }

    private fun getWinningLotto(winningNumbers: Lotto): WinningLotto {
        return runCatching {
            val input = inputView.readBonusNumber()
            WinningLotto(winningNumbers, LottoNumber.from(input))
        }.getOrElse { e ->
            outputView.printErrorMessage(e.message)
            getWinningLotto(winningNumbers)
        }
    }

    private fun displayResult(
        lottoResult: LottoMatchResult,
        profitRate: Double,
    ) {
        outputView.printWinningResult(lottoResult, profitRate)
        if (profitRate < 1) outputView.printLossMessage()
    }
}
