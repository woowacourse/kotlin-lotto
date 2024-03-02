package controller

import entity.Ticket
import model.Amount
import model.Bonus
import model.Lottery
import model.LotteryMachine
import model.ManualLotteryCount
import model.PurchaseInformation
import model.WinningResult
import utils.RandomLotteriesGenerationStrategy
import view.InputView
import view.OutputView

class LotteryController {
    fun start() {
        val amount = retryUntilSuccess { readAmount() }

        val manualLotteryCount = retryUntilSuccess { readManualLotteryCount(amount) }
        val purchaseInformation = PurchaseInformation(amount, manualLotteryCount)

        val manualLotteriesInput = readManualLotteries(manualLotteryCount)
        val manualLotteries = splitManualLotteries(manualLotteriesInput)

        val ticket = issueTicket(manualLotteries, RandomLotteriesGenerationStrategy(purchaseInformation), purchaseInformation)
        printTicketInfo(ticket)

        val winningLottoInput = retryUntilSuccess { readWinningLotto() }
        val winningLotto = splitWinningLotto(winningLottoInput)
        val bonus = retryUntilSuccess { readBonus(winningLotto) }

        val winningResult = getWinningResult(ticket, winningLotto, bonus)
        printWinningResult(winningResult)
    }

    private fun readAmount() = Amount.fromInput(InputView.readAmount())

    private fun readManualLotteryCount(
        amount: Amount,
        lotteryTicketPrice: Int = LOTTO_TICKET_PRICE,
    ) = ManualLotteryCount.fromInput(
        InputView.readManualCount(),
        amount,
        lotteryTicketPrice,
    )

    private fun readManualLotteries(manualLotteryCount: ManualLotteryCount) = InputView.readManualLotteries(manualLotteryCount)

    private fun splitManualLotteries(input: List<String>): List<List<String>> {
        val splitManualLotteries = InputView.splitManualLotteries(input)
        val manualLotteries = mutableListOf<List<String>>()

        splitManualLotteries.forEach {
            manualLotteries.add(it)
        }
        return manualLotteries
    }

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(InputView.readBonus(), winningLottery)

    private fun readWinningLotto() = InputView.readWinningLotto()

    private fun splitWinningLotto(input: String) = Lottery.fromInput(InputView.splitWinningLotto(input))

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
        private const val LOTTO_TICKET_PRICE = 1000
    }
}
