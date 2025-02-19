package controller

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.PurchasePrice
import domain.model.WinningLotto
import domain.service.LottoGenerator
import lotto.util.retryWhenException
import validator.NumericValidator
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice = getPurchasePrice()
        val lotto = buyLotto(purchasePrice)
        displayBuyLotto(lotto)
        val winningNumbers = getWinningNumbers()
        val winningLotto = getWinningLotto(winningNumbers)
    }

    fun getPurchasePrice(): PurchasePrice {
        return retryWhenException(
            action = {
                val input = inputView.readPurchasePrice()
                NumericValidator(input)
                PurchasePrice(input.toInt())
            },
            onError = { outputView.printErrorMessage(it) },
        )
    }

    fun buyLotto(money: PurchasePrice): PurchaseLotto {
        val generator = LottoGenerator(money)
        val lotto = generator.makeLotto()
        return lotto
    }

    fun displayBuyLotto(lotto: PurchaseLotto) {
        outputView.printPurchasedLottoCount(lotto.values.size)
        outputView.printPurchasedLotto(lotto.toString())
    }

    fun getWinningNumbers(): Lotto {
        return retryWhenException(
            action = {
                val input = inputView.readWinningNumbers()
                Lotto(input.split(",").map { it.trim().toInt() })
            },
            onError = { outputView.printErrorMessage(it) },
        )
    }

    fun getWinningLotto(winningNumbers: Lotto): WinningLotto {
        return retryWhenException(
            action = {
                val input = inputView.readBonusNumber()
                NumericValidator(input)
                val bonusNumber = BonusNumber(input.toInt())
                WinningLotto(winningNumbers, bonusNumber)
            },
            onError = { outputView.printErrorMessage(it) },
        )
    }
}
