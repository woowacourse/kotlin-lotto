package lotto.controller

import lotto.domain.LotteriesGenerator
import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.LotteryNumber
import lotto.domain.ManualLotteryGenerator
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

        val tickets = publishTickets(purchase)

        val winningLottery = getWinningLottery()

        announceResult(tickets, winningLottery, purchase)
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

        val manualTickets = publishManualTickets(manualQuantity)
        val autoTickets = publishAutoTickets(autoQuantity)
        val tickets = manualTickets + autoTickets
        outputView.printLotteryTickets(manualQuantity, tickets)
        outputView.printInterval()
        return tickets
    }

    private fun publishManualTickets(quantity: Int): List<Lottery> {
        val manualNumbers = inputView.readManualLotteryNumbers(quantity)
        val manualTickets = LotteriesGenerator().generateManually(quantity, ManualLotteryGenerator(manualNumbers))
        outputView.printInterval()
        return manualTickets
    }

    private fun publishAutoTickets(quantity: Int): List<Lottery> {
        return LotteriesGenerator().generate(quantity, generator)
    }

    private fun getWinningLottery(): WinningLottery {
        val winningNumbers = Lottery(inputView.readWinningNumbers().map { LotteryNumber.from(it) })
        val bonusNumber = LotteryNumber.from(inputView.readBonusNumber())
        outputView.printInterval()
        return WinningLottery(winningNumbers, bonusNumber)
    }

    private fun announceResult(tickets: List<Lottery>, lottery: WinningLottery, purchase: PurchaseAmount) {
        val countResult = RankCounter.count(tickets, lottery)
        val prize = ResultCalculator.calculateTotalPrize(countResult)
        val profit = ResultCalculator.calculateProfit(purchase, prize)
        outputView.printWinningResult(countResult, profit)
    }
}
