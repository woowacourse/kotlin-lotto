package controller

import entity.Ticket
import model.Amount
import model.Bonus
import model.Lottery
import model.LotteryPurchasePattern
import model.LotteryStore
import model.WinningResult
import utils.MixedTicketGenerationStrategy
import view.InputView
import view.OutputView

class LotteryController {
    fun start() {
        val amount = readAmount()

        val lotteryPurchasePattern = readPurchasePattern(amount)

        val manualLotteryCandidates = readManualLotteryCandidates(lotteryPurchasePattern)

        val ticket = issueTicket(amount, manualLotteryCandidates, lotteryPurchasePattern)
        printTicketInfo(ticket)

        val winningLotto = readWinningLotto()
        val bonus = readBonus(winningLotto)

        val winningResult = getWinningResult(ticket, winningLotto, bonus)
        printWinningResult(winningResult)
    }

    private fun readAmount() = Amount.fromInput(InputView.readAmount())

    private fun readPurchasePattern(amount: Amount) = LotteryPurchasePattern.ofManual(amount, InputView.readPurchasePattern())

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(InputView.readBonus(), winningLottery)

    private fun readManualLotteryCandidates(lotteryPurchasePattern: LotteryPurchasePattern) =
        InputView.readManualLotteryCandidates(lotteryPurchasePattern)

    private fun readWinningLotto() = Lottery.fromInput(InputView.readWinningLotto())

    private fun issueTicket(
        amount: Amount,
        manualLotteryCandidates: List<String>,
        lotteryPurchasePattern: LotteryPurchasePattern,
    ): Ticket {
        return LotteryStore().setStrategy(
            MixedTicketGenerationStrategy(
                amount,
                manualLotteryCandidates,
                lotteryPurchasePattern,
            ),
        ).issueTicket()
    }

    private fun printTicketInfo(ticket: Ticket) = OutputView.printTicketInfo(ticket)

    private fun printWinningResult(winningResult: WinningResult) = OutputView.printWinningResult(winningResult)

    private fun getWinningResult(
        ticket: Ticket,
        winningLottery: Lottery,
        bonus: Bonus,
    ) = WinningResult.of(ticket, winningLottery, bonus)
}
