package controller

import entity.Ticket
import model.Amount
import model.Bonus
import model.Lottery
import model.LotteryStore
import model.WinningResult
import utils.RandomTicketGenerationStrategy
import view.InputView
import view.OutputView

class LotteryController {
    fun start() {
        val amount = readAmount()

        val ticket = issueTicket(amount)
        printTicketInfo(ticket)

        val winningLottoInput = readWinningLotto()
        val winningLotto = splitWinningLotto(winningLottoInput)
        val bonus = readBonus(winningLotto)

        val winningResult = getWinningResult(ticket, winningLotto, bonus)
        printWinningResult(winningResult)
    }

    private fun readAmount() = Amount.fromInput(InputView.readAmount())

    private fun readBonus(winningLottery: Lottery) = Bonus.fromInput(InputView.readBonus(), winningLottery)

    private fun readWinningLotto() = InputView.readWinningLotto()

    private fun splitWinningLotto(input: String) = Lottery.fromInput(InputView.splitWinningLotto(input))

    private fun issueTicket(amount: Amount): Ticket {
        return LotteryStore(RandomTicketGenerationStrategy(amount)).issueTicket()
    }

    private fun printTicketInfo(ticket: Ticket) = OutputView.printTicketInfo(ticket)

    private fun printWinningResult(winningResult: WinningResult) = OutputView.printWinningResult(winningResult)

    private fun getWinningResult(
        ticket: Ticket,
        winningLottery: Lottery,
        bonus: Bonus,
    ) = WinningResult.of(ticket, winningLottery, bonus)
}
