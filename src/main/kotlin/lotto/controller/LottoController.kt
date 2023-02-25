package lotto.controller

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
    private val machine: LotteryMachine = LotteryMachine()
) {
    fun run() {
        val amount: PurchaseAmount = inputView.readPurchaseAmount()
        val lotteries: Lotteries = createLotteries(amount)

        val winningLottery: WinningLottery = WinningLottery(inputView.readWinningLottery(), inputView.readBonusNumber())
        val result: WinningResult = machine.createWinningResult(winningLottery, lotteries, amount)

        outputView.printWinningStats(result)
    }

    private fun createLotteries(amount: PurchaseAmount): Lotteries {
        val autoLotteries: Lotteries = inputView.readLotteries(amount.autoNumber)
        val manualLotteries: Lotteries = machine.generateLotteries(amount.manualNumber)
        val lotteries: Lotteries = autoLotteries.plus(manualLotteries)
        outputView.printPurchaseLotteries(amount, lotteries)
        return lotteries
    }
}
