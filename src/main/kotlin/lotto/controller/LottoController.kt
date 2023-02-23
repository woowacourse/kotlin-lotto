package lotto.controller

import lotto.LotterySeller
import lotto.domain.Lottery
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLottery
import lotto.domain.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val seller: LotterySeller = LotterySeller()
) {
    fun run() {
        val purchaseAmount: PurchaseAmount = inputView.readPurchaseAmount()
        val lotteries: List<Lottery> = seller.generateLotteries(purchaseAmount)
        repeat(lotteries.size) { outputView.printMessage(lotteries[it].numbers.toString()) }

        val winningLottery: WinningLottery = WinningLottery(inputView.readLottery(), inputView.readBonusNumber())
        val result: WinningResult = winningLottery.createResult(lotteries, purchaseAmount.toInt())

        outputView.printWinningStats(result)
    }
}
