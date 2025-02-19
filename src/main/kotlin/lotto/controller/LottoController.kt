package lotto.controller

import lotto.LottoPurchaseAmount
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseMoney = getPurchaseMoney()
    }

    private fun getPurchaseMoney(): LottoPurchaseAmount =
        try {
            outputView.printPurchaseAmountGuide()
            val money = inputView.readLottoPurchaseAmount()
            LottoPurchaseAmount(money)
        } catch (error: IllegalArgumentException) {
            outputView.printErrorMessage(error.message ?: "")
            getPurchaseMoney()
        }
}
