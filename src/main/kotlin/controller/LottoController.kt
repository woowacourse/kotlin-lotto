package controller

import model.*
import view.InputView
import view.OutputView

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

        val manualLottoTicket = askUserForManualLottoTickets(manualLottoCount)
        val autoLottoTicket = generateAutoLottoTickets(autoLottoCount)

        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        val lottoTickets = manualLottoTicket + autoLottoTicket
        outputView.printLottoTickets(lottoTickets)

        val lottoWinning = makeLottoWinning()
        val lottoResult = lottoWinning.makeLottoResult(lottoTickets)
        val profitRate = lottoResult.winningMoney / purchasePrice ?: 0f
        printWinningResult(lottoResult, profitRate)
    }

    private fun askUserForManualLottoTickets(count: Int): List<LottoTicket> =
        inputView.getManualLottoTickets(count)

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
