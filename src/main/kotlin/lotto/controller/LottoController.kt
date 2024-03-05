package lotto.controller

import lotto.model.LottoCounter
import lotto.model.LottoResult
import lotto.model.LottoTicket
import lotto.model.LottoTicketFactory
import lotto.model.LottoWinning
import lotto.model.Money
import lotto.model.RandomLottoTicketGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice = getPurchasePrice()
        val lottoCounter = LottoCounter(purchasePrice)
        val (manualLottoCount, autoLottoCount) = getCounts(lottoCounter)
        val lottoTickets = generateLottoTickets(manualLottoCount, autoLottoCount)
        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        outputView.printLottoTickets(lottoTickets)

        val lottoWinning = makeLottoWinning()
        val lottoResult = lottoWinning.makeLottoResult(lottoTickets)
        val profitRate = getProfitRate(lottoResult.winningMoney, purchasePrice)
        printWinningResult(lottoResult, profitRate)
    }

    private fun getProfitRate(
        purchasePrice: Money,
        winningMoney: Money,
    ): Float {
        return (winningMoney / purchasePrice) ?: 0f
    }

    private fun getPurchasePrice(): Money {
        return inputView.getPurchasePrice()
    }

    private fun getManualCount(): Int {
        return inputView.getManualCount()
    }

    private tailrec fun getCounts(lottoCounter: LottoCounter): Pair<Int, Int> {
        val manualLottoCount = getManualCount()
        val autoLottoCount = lottoCounter.getAutoCountOrNull(manualLottoCount)
        if (autoLottoCount != null) {
            return manualLottoCount to autoLottoCount
        }
        return getCounts(lottoCounter)
    }

    private fun generateLottoTickets(
        manualLottoCount: Int,
        autoLottoCount: Int,
    ): List<LottoTicket> {
        val manualLottoTicket = generateManualLottoTickets(manualLottoCount)
        val autoLottoTicket = generateAutoLottoTickets(autoLottoCount)
        return manualLottoTicket + autoLottoTicket
    }

    private fun generateManualLottoTickets(count: Int): List<LottoTicket> {
        val ticketIterator = inputView.getManualLottoTickets(count).iterator()
        val lottoTicketFactory = LottoTicketFactory { ticketIterator.next() }
        return lottoTicketFactory.makeLottoTickets(count)
    }

    private fun generateAutoLottoTickets(count: Int): List<LottoTicket> {
        val lottoTicketFactory = LottoTicketFactory(RandomLottoTicketGenerator)
        return lottoTicketFactory.makeLottoTickets(count)
    }

    private fun makeLottoWinning(): LottoWinning {
        val winningTicket = inputView.getWinningTicket()
        val bonusNumber = inputView.getBonusNumber()
        return LottoWinning(winningTicket, bonusNumber)
    }

    private fun printWinningResult(
        lottoResult: LottoResult,
        profitRate: Float,
    ) {
        outputView.printWinningChart(lottoResult)
        outputView.printWinningRate(profitRate)
    }
}
