package lotto.controller

import lotto.LotterySeller
import lotto.domain.Lotteries
import lotto.domain.LotteryMachine
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLottery
import lotto.domain.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val seller: LotterySeller = LotterySeller(),
    private val machine: LotteryMachine = LotteryMachine()
) {
    fun run() {
        val purchaseAmount: PurchaseAmount = inputView.readPurchaseAmount()
        val lotteries: Lotteries = Lotteries(seller.generateLotteries(purchaseAmount))

        repeat(lotteries.size) { outputView.printMessage(lotteries.get(it).numbers.toString()) }

        val winningLottery: WinningLottery = WinningLottery(inputView.readLottery(), inputView.readBonusNumber())
        val result: WinningResult = machine.createWinningResult(winningLottery, lotteries, purchaseAmount)

        outputView.printWinningStats(result)
    }
}
