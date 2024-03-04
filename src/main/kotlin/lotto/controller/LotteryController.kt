package lotto.controller

import lotto.model.LotteryMachine
import lotto.model.Ticket
import lotto.model.lottery.Bonus
import lotto.model.lottery.Lottery
import lotto.model.lottery.strategy.RandomLotteriesGenerationStrategy
import lotto.model.puchaseinformation.PurchaseInformation
import lotto.model.winning.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LotteryController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val purchaseInformation = retryUntilSuccess { getPurchaseInformation() }
        val ticket = generateTicket(purchaseInformation)
        printTicketInfo(ticket)

        val (winningLottery, bonus) = getWinningLotteryAndBonus()
        val winningResult = getWinningResult(ticket, winningLottery, bonus)
        printWinningResult(winningResult)
    }

    private fun getAmount(): PurchaseInformation = PurchaseInformation(readAmount())

    private fun getPurchaseInformation(): PurchaseInformation = PurchaseInformation(getAmount().amount, readManualLotteryCount())

    private fun generateTicket(purchaseInformation: PurchaseInformation): Ticket {
        val manualLotteries = readManualLotteries(purchaseInformation)

        return issueTicket(manualLotteries, RandomLotteriesGenerationStrategy(purchaseInformation), purchaseInformation)
    }

    private fun printTicketInfo(ticket: Ticket) = outputView.printTicketInfo(ticket)

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

    private fun printWinningResult(winningResult: WinningResult) = outputView.printWinningResult(winningResult)

    private fun readAmount(): Int {
        val amount = inputView.readAmount()

        if (amount != null) return amount

        outputView.printError(EXCEPTION_IS_NOT_NUMBER)
        return readAmount()
    }

    private fun readManualLotteryCount(): Int {
        val manualLotteryCount = inputView.readManualCount()

        if (manualLotteryCount != null) return manualLotteryCount

        outputView.printError(EXCEPTION_IS_NOT_NUMBER)
        return readManualLotteryCount()
    }

    private fun readManualLotteries(purchaseInformation: PurchaseInformation) = inputView.readManualLotteries(purchaseInformation)

    private fun issueTicket(
        manualLotteries: List<List<String>>,
        strategyForAuto: RandomLotteriesGenerationStrategy,
        purchaseInformation: PurchaseInformation,
    ): Ticket = LotteryMachine.issueTicket(manualLotteries, strategyForAuto, purchaseInformation)

    private fun readWinningLottery() = Lottery.fromInput(inputView.readWinningLottery())

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(inputView.readBonus(), winningLottery)

    private fun <T> retryUntilSuccess(action: () -> T): T =
        runCatching {
            action()
        }.getOrElse {
            outputView.printErrorMessage(it)
            retryUntilSuccess(action)
        }

    companion object {
        private const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
    }
}
