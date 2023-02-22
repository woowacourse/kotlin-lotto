package lotto.controller

import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLottery
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryGenerator: LotteryGenerator = LotteryGenerator()
) {
    fun run() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val lotteries = createLotteries(purchaseAmount)
        val winningLottery = WinningLottery(inputView.readLottery(), inputView.readBonusNumber())
        val winningResult = winningLottery.createResult(lotteries)

        outputView.printWinningStats(purchaseAmount, winningResult)
    }

    private fun createLotteries(purchaseAmount: PurchaseAmount): List<Lottery> {
        val lotteries = lotteryGenerator.generateLotteries(purchaseAmount.getPurchaseQuantity())

        repeat(lotteries.size) { outputView.printMessage(lotteries[it].numbers.toString()) }

        return lotteries
    }
}
