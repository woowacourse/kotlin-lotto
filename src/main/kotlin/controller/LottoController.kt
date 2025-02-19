package controller

import domain.generator.LottoGenerator
import domain.model.PurchaseLotto
import domain.model.PurchasePrice
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
}
