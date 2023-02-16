package lotto.controller

import lotto.domain.LotteriesGenerator
import lotto.domain.LotteryNumberGenerator
import lotto.domain.PurchaseAmount
import lotto.view.InputView
import lotto.view.OutputView

class Controller(
    private val generator: LotteryNumberGenerator,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        publishLotteries()
    }

    private fun publishLotteries() {
        val money = PurchaseAmount(inputView.readPurchaseAmount())
        val lotteries = LotteriesGenerator().generate(generator, money.getPurchaseQuantity())
        outputView.printLotteries(lotteries)
        outputView.printInterval()
    }
}
