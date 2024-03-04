package lotto.controller

import lotto.model.LotteryMachine
import lotto.model.Ticket
import lotto.model.lottery.Bonus
import lotto.model.lottery.Lottery
import lotto.model.lottery.strategy.RandomLotteriesGenerationStrategy
import lotto.model.puchaseinformation.Amount
import lotto.model.puchaseinformation.ManualLotteryCount
import lotto.model.puchaseinformation.PurchaseInformation
import lotto.model.winning.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LotteryController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
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

    private fun readAmount(): Amount {
        val amount = inputView.readAmount()

        if (amount != null) return Amount(amount)

        outputView.printError(EXCEPTION_IS_NOT_NUMBER)
        return readAmount()
    }

    private fun readManualLotteryCount(
        amount: Amount,
        lotteryTicketPrice: Int = LOTTERY_TICKET_PRICE,
    ): ManualLotteryCount {
        while (true) {
            val manualLotteryCount =
                ManualLotteryCount.fromInput(
                    inputView.readManualCount(),
                    amount,
                    lotteryTicketPrice,
                )

            if (manualLotteryCount != null) return manualLotteryCount
            OutputView.printError(EXCEPTION_IS_NOT_NUMBER)
        }
    }

    private fun readManualLotteries(manualLotteryCount: ManualLotteryCount) = inputView.readManualLotteries(manualLotteryCount)

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
        private const val LOTTERY_TICKET_PRICE = 1000
        private const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
    }
}
