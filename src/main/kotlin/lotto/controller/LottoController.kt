package lotto.controller

import lotto.model.LottoCounter
import lotto.model.LottoResult
import lotto.model.LottoTicket
import lotto.model.LottoTicketFactory
import lotto.model.LottoWinning
import lotto.model.RandomLottoTicketGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoCounter = LottoCounter(purchasePrice)

        val manualLottoCount = inputView.getManualCount()
        lottoCounter.reduce(manualLottoCount)
        val autoLottoCount = lottoCounter.remain

        val manualLottoTicket = generateManualLottoTickets(manualLottoCount)
        val autoLottoTicket = generateAutoLottoTickets(autoLottoCount)

        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        val lottoTickets = manualLottoTicket + autoLottoTicket
        outputView.printLottoTickets(lottoTickets)

        val lottoWinning = makeLottoWinning()
        val lottoResult = lottoWinning.makeLottoResult(lottoTickets)
        val profitRate = lottoResult.winningMoney / purchasePrice ?: 0f
        printWinningResult(lottoResult, profitRate)
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
