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
        val receipt = pay()
        outputView.printInterval()

        val tickets = buyTickets(receipt)
        outputView.printInterval()

        val winningLottery = getWinningLottery()
        outputView.printInterval()

        announceResult(tickets, winningLottery, receipt.purchase)
    }

    private fun pay(): Receipt {
        val purchase = getPurchase()
        outputView.printInterval()
        return getReceipt(purchase)
    }

    private fun getPurchase(): PurchaseAmount {
        return runCatching {
            val amount = inputView.readPurchaseAmount()
            PurchaseAmount(amount)
        }.getOrElse {
            outputView.printError(it.message ?: "")
            getPurchase()
        }
    }

    private fun getReceipt(purchase: PurchaseAmount): Receipt {
        return runCatching {
            val count = TicketCount(inputView.readManualLotteryCount())
            Receipt(purchase, count)
        }.getOrElse {
            outputView.printError(it.message ?: "")
            getReceipt(purchase)
        }
    }

    private fun buyTickets(receipt: Receipt): List<Lottery> {
        val manualTickets = buyManualTickets(receipt.manual.count)
        val autoTickets = buyAutoTickets(receipt.auto.count)
        val tickets = manualTickets + autoTickets
        outputView.printLotteryTickets(receipt, tickets)
        return tickets
    }

    private fun buyManualTickets(count: Int): List<Lottery> {
        if (count == 0) {
            return listOf()
        }

        val tickets = publishManualTickets(count)
        outputView.printInterval()
        return tickets
    }

    private fun publishManualTickets(count: Int): List<Lottery> {
        return runCatching {
            val manualNumbers = inputView.readManualLotteryNumbers(count)
            val ticketsMachine = ManualLotteryTicketsMachine(manualNumbers)
            ticketsMachine.generate()
        }.getOrElse {
            outputView.printError(it.message ?: "")
            buyManualTickets(count)
        }
    }

    private fun buyAutoTickets(count: Int): List<Lottery> {
        val ticketsMachine = AutoLotteryTicketsMachine(count)
        return ticketsMachine.generate()
    }

    private fun getWinningLottery(): WinningLottery {
        return runCatching {
            val winningNumbers = Lottery.from(inputView.readWinningNumbers())
            val bonusNumber = LotteryNumber.from(inputView.readBonusNumber())
            WinningLottery(winningNumbers, bonusNumber)
        }.getOrElse {
            outputView.printError(it.message ?: "")
            getWinningLottery()
        }
    }

    private fun announceResult(tickets: List<Lottery>, lottery: WinningLottery, purchase: PurchaseAmount) {
        val result = LotteryResult(tickets, lottery)
        val profit = result.getProfit(purchase)
        outputView.printWinningResult(result.ranks, profit)
    }
}
