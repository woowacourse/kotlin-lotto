package lotto.controller

import lotto.domain.AutoLotteryTicketsMachine
import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.LotteryResult
import lotto.domain.ManualLotteryTicketsMachine
import lotto.domain.PurchaseAmount
import lotto.domain.Receipt
import lotto.domain.TicketCount
import lotto.domain.WinningLottery
import lotto.view.InputView
import lotto.view.OutputView

object Controller {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val receipt = buy()
        outputView.printInterval()

        val tickets = publishTickets(receipt)
        outputView.printInterval()

        val winningLottery = getWinningLottery()
        outputView.printInterval()

        announceResult(tickets, winningLottery, receipt.purchase)
    }

    private fun buy(): Receipt {
        val purchase = getPurchase()
        outputView.printInterval()
        return getReceipt(purchase)
    }

    private fun getPurchase(): PurchaseAmount {
        return runCatching {
            val amount = inputView.readPurchaseAmount()
            PurchaseAmount(amount)
        }.getOrElse {
            inputView.printError(it.message ?: "")
            getPurchase()
        }
    }

    private fun getReceipt(purchase: PurchaseAmount): Receipt {
        return runCatching {
            val count = TicketCount(inputView.readManualLotteryCount())
            Receipt(purchase, count)
        }.getOrElse {
            inputView.printError(it.message ?: "")
            getReceipt(purchase)
        }
    }

    private fun publishTickets(receipt: Receipt): List<Lottery> {
        val manualTickets = buildList {
            if (receipt.manual.count > 0) {
                addAll(publishManualTickets(receipt.manual.count))
                outputView.printInterval()
            }
        }
        val autoTickets = publishAutoTickets(receipt.auto.count)
        val tickets = manualTickets + autoTickets
        outputView.printLotteryTickets(receipt, tickets)
        return tickets
    }

    private fun publishManualTickets(count: Int): List<Lottery> {
        return runCatching {
            val manualNumbers = inputView.readManualLotteryNumbers(count)
            val ticketsMachine = ManualLotteryTicketsMachine(manualNumbers)
            ticketsMachine.generate()
        }.getOrElse {
            inputView.printError(it.message ?: "")
            publishManualTickets(count)
        }
    }

    private fun publishAutoTickets(count: Int): List<Lottery> {
        val ticketsMachine = AutoLotteryTicketsMachine(count)
        return ticketsMachine.generate()
    }

    private fun getWinningLottery(): WinningLottery {
        return runCatching {
            val winningNumbers = Lottery.from(inputView.readWinningNumbers())
            val bonusNumber = LotteryNumber.from(inputView.readBonusNumber())
            WinningLottery(winningNumbers, bonusNumber)
        }.getOrElse {
            inputView.printError(it.message ?: "")
            getWinningLottery()
        }
    }

    private fun announceResult(tickets: List<Lottery>, lottery: WinningLottery, purchase: PurchaseAmount) {
        val ranks = LotteryResult.getRanks(tickets, lottery)
        val prize = LotteryResult.getTotalPrize(ranks)
        val profit = LotteryResult.getProfit(purchase, prize)
        outputView.printWinningResult(ranks, profit)
    }
}
