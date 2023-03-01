package lotto.controller

import lotto.domain.Lotteries
import lotto.domain.Lottery
import lotto.domain.LotteryMachine
import lotto.domain.LotteryNumber
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
        val amount: PurchaseAmount = getPurchaseAmount()
        val lotteries: Lotteries = machine.createLotteries(getLotteries(amount.manualNumber), amount.autoNumber)
        outputView.printPurchaseLotteries(amount, lotteries)

        val winningLottery: WinningLottery = getWinningLottery()
        val result: WinningResult = winningLottery.createWinningResult(lotteries, amount)
        outputView.printWinningStats(result)
    }

    private fun getWinningLottery(): WinningLottery = WinningLottery(getLottery(), getLotteryNumber())

    private fun getLottery(): Lottery = inputView.readLottery() ?: getLottery()

    private fun getLotteryNumber(): LotteryNumber = inputView.readLotteryNumber() ?: getLotteryNumber()

    private fun getPurchaseAmount(): PurchaseAmount = inputView.readPurchaseAmount() ?: getPurchaseAmount()

    private fun getLotteries(count: Int): Lotteries = inputView.readLotteries(count) ?: getLotteries(count)
}
