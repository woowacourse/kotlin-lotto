package controller

import model.Amount
import model.Bonus
import model.Lottery
import model.LotteryMachine
import model.ManualLotteryCount
import model.PurchaseInformation
import model.Ticket
import model.WinningResult
import model.generator.RandomLotteriesGenerationStrategy
import view.InputView
import view.OutputView

class LotteryController {
    fun start() {
        val purchaseInformation = getPurchaseInformation()
        val ticket = generateTicket(purchaseInformation)
        printTicketInfo(ticket)

        val (winningLottery, bonus) = getWinningLotteryAndBonus()
        val winningResult = getWinningResult(ticket, winningLottery, bonus)
        printWinningResult(winningResult)
    }

    private fun getPurchaseInformation(): PurchaseInformation {
        val amount = retryUntilSuccess { readAmount() }
        val manualLotteryCount = retryUntilSuccess { readManualLotteryCount(amount) }

        return PurchaseInformation(amount, manualLotteryCount)
    }

    private fun generateTicket(purchaseInformation: PurchaseInformation): Ticket {
        val manualLotteries = readManualLotteries(purchaseInformation.manualLotteryCount)

        return issueTicket(manualLotteries, RandomLotteriesGenerationStrategy(purchaseInformation), purchaseInformation)
    }

    private fun getWinningLotteryAndBonus(): Pair<Lottery, Bonus> {
        val winningLottery = retryUntilSuccess { readWinningLottery() }
        val bonus = retryUntilSuccess { readBonus(winningLottery) }

        return Pair(winningLottery, bonus)
    }

    private fun readAmount() = Amount.fromInput(InputView.readAmount())

    private fun readManualLotteryCount(
        amount: Amount,
        lotteryTicketPrice: Int = LOTTERY_TICKET_PRICE,
    ) = ManualLotteryCount.fromInput(
        InputView.readManualCount(),
        amount,
        lotteryTicketPrice,
    )

    private fun readManualLotteries(manualLotteryCount: ManualLotteryCount) = InputView.readManualLotteries(manualLotteryCount)

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(InputView.readBonus(), winningLottery)

    private fun readWinningLottery() = Lottery.fromInput(InputView.readWinningLottery())

    private fun issueTicket(
        manualLotteries: List<List<String>>,
        strategyForAuto: RandomLotteriesGenerationStrategy,
        purchaseInformation: PurchaseInformation,
    ): Ticket = LotteryMachine.issueTicket(manualLotteries, strategyForAuto, purchaseInformation)

    private fun printTicketInfo(ticket: Ticket) = OutputView.printTicketInfo(ticket)

    private fun printWinningResult(winningResult: WinningResult) = OutputView.printWinningResult(winningResult)

    private fun getWinningResult(
        ticket: Ticket,
        winningLottery: Lottery,
        bonus: Bonus,
    ) = WinningResult.of(ticket, winningLottery, bonus)

    private fun <T> retryUntilSuccess(action: () -> T): T =
        runCatching {
            action()
        }.getOrElse {
            OutputView.printErrorMessage(it)
            retryUntilSuccess(action)
        }

    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
    }
}
