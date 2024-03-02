package lotto.controller

import lotto.model.Amount
import lotto.model.Bonus
import lotto.model.Lottery
import lotto.model.LotteryMachine
import lotto.model.ManualLotteryCount
import lotto.model.PurchaseInformation
import lotto.model.Ticket
import lotto.model.WinningResult
import lotto.model.generator.RandomLotteriesGenerationStrategy
import lotto.view.InputView
import lotto.view.OutputView

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

    private fun printTicketInfo(ticket: Ticket) = OutputView.printTicketInfo(ticket)

    private fun getWinningLotteryAndBonus(): Pair<Lottery, Bonus> {
        val winningLottery = retryUntilSuccess { readWinningLottery() }
        val bonus = retryUntilSuccess { readBonus(winningLottery) }

        return Pair(winningLottery, bonus)
    }

    private fun getWinningResult(
        ticket: Ticket,
        winningLottery: Lottery,
        bonus: Bonus,
    ) = WinningResult.of(ticket, winningLottery, bonus)

    private fun printWinningResult(winningResult: WinningResult) = OutputView.printWinningResult(winningResult)

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

    private fun issueTicket(
        manualLotteries: List<List<String>>,
        strategyForAuto: RandomLotteriesGenerationStrategy,
        purchaseInformation: PurchaseInformation,
    ): Ticket = LotteryMachine.issueTicket(manualLotteries, strategyForAuto, purchaseInformation)

    private fun readWinningLottery() = Lottery.fromInput(InputView.readWinningLottery())

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(InputView.readBonus(), winningLottery)

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
