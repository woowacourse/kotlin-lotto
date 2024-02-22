package controller

import entity.Ticket
import model.*
import utils.RandomTicketGenerationStrategy
import view.InputView
import view.OutputView

class LotteryController {
    fun start() {
        val amount = readAmount()

        val ticket = issueTicket(amount)
        printTicketInfo(ticket)

        val winningLotto = readWinningLotto()
        val bonus = readBonus(winningLotto)

        val winningResult = getWinningResult(ticket, winningLotto, bonus)
        printWinningResult(winningResult)
    }

    private fun readAmount() = Amount.fromInput(InputView.readAmount())

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(InputView.readBonus(), winningLottery)

    private fun readWinningLotto() = Lottery.fromInput(InputView.readWinningLotto())

    private fun issueTicket(amount: Amount): Ticket {
        return LotteryStore().setStrategy(RandomTicketGenerationStrategy(amount)).issueTicket()
    }

    private fun printTicketInfo(ticket: Ticket) = OutputView.printTicketInfo(ticket)

    private fun printWinningResult(winningResult: WinningResult) = OutputView.printWinningResult(winningResult)

    private fun getWinningResult(
        ticket: Ticket,
        winningLottery: Lottery,
        bonus: Bonus,
    ) = WinningResult.of(ticket, winningLottery, bonus)
}
