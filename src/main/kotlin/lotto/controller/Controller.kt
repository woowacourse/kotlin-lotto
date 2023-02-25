package lotto.controller

import lotto.domain.AutoLotteryTicketsMachine
import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.LotteryResult
import lotto.domain.ManualLotteryTicketsMachine
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLottery
import lotto.view.InputView
import lotto.view.OutputView

object Controller {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val purchase = getPurchase()
        outputView.printInterval()

        val tickets = purchaseTickets(purchase)
        outputView.printInterval()

        val winningLottery = getWinningLottery()
        outputView.printInterval()

        announceResult(tickets, winningLottery, purchase)
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

    private fun purchaseTickets(purchase: PurchaseAmount): List<Lottery> {
        val manualQuantity = getQuantity(purchase)
        outputView.printInterval()

        val autoQuantity = purchase.getAutoPurchaseQuantity(manualQuantity)
        return publishTickets(manualQuantity, autoQuantity)
    }

    private fun getQuantity(purchase: PurchaseAmount): Int {
        return runCatching {
            val manualQuantity = inputView.readManualLotteryQuantity()
            purchase.checkQuantity(manualQuantity)
            manualQuantity
        }.getOrElse {
            inputView.printError(it.message ?: "")
            getQuantity(purchase)
        }
    }

    private fun publishTickets(manualQuantity: Int, autoQuantity: Int): List<Lottery> {
        val manualTickets = mutableListOf<Lottery>()
        if (manualQuantity > 0) {
            manualTickets.addAll(publishManualTickets(manualQuantity))
            outputView.printInterval()
        }

        val autoTickets = publishAutoTickets(autoQuantity)
        val tickets = manualTickets + autoTickets
        outputView.printLotteryTickets(manualQuantity, tickets)
        return tickets
    }

    private fun publishManualTickets(quantity: Int): List<Lottery> {
        return runCatching {
            val manualNumbers = inputView.readManualLotteryNumbers(quantity)
            val ticketsMachine = ManualLotteryTicketsMachine(manualNumbers)
            val manualTickets = ticketsMachine.generate()
            manualTickets
        }.getOrElse {
            inputView.printError(it.message ?: "")
            publishManualTickets(quantity)
        }
    }

    private fun publishAutoTickets(quantity: Int): List<Lottery> {
        val ticketsMachine = AutoLotteryTicketsMachine(quantity)
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
