package lotto.controller

import lotto.domain.LotteriesGenerator
import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.LotteryNumber
import lotto.domain.PurchaseAmount
import lotto.domain.RankCounter
import lotto.domain.ResultCalculator
import lotto.domain.WinningLottery
import lotto.view.InputView
import lotto.view.OutputView

class Controller(
    private val generator: LotteryGenerator,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val purchase = getPurchase()

        val lotteries = publishTickets(purchase)

        val winningLottery = getWinningLottery()
        outputView.printInterval()

        val countResult = RankCounter.count(lotteries, winningLottery)
        val prize = ResultCalculator.calculateTotalPrize(countResult)
        val profit = ResultCalculator.calculateProfit(purchase, prize)
        outputView.printWinningResult(countResult, profit)
    }

    private fun getPurchase(): PurchaseAmount {
        val amount = inputView.readPurchaseAmount()
        outputView.printInterval()
        return PurchaseAmount(amount)
    }

    private fun publishTickets(purchase: PurchaseAmount): List<Lottery> {
        val manualQuantity = inputView.readManualLotteryQuantity()
        val autoQuantity = purchase.getAutoPurchaseQuantity(manualQuantity)
        outputView.printInterval()

        val autoTickets = LotteriesGenerator().generate(autoQuantity, generator)
        val tickets = autoTickets
        outputView.printLotteries(tickets)
        outputView.printInterval()
        return tickets
    }

    private fun getWinningLottery(): WinningLottery {
        val winningNumbers = Lottery(inputView.readWinningNumbers().map { LotteryNumber.from(it) })
        val bonusNumber = LotteryNumber.from(inputView.readBonusNumber())
        return WinningLottery(winningNumbers, bonusNumber)
    }
}
