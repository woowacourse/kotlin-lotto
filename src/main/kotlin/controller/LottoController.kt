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

        val wholeLottoCount = getLottoCount(purchasePrice)

        val manualLottoCount = inputView.getManualCount()
        val manualLottoTicket = askUserForManualLottoTickets(manualLottoCount)

        val autoLottoCount = wholeLottoCount - manualLottoCount
        val autoLottoTicket = generateAutoLottoTickets(autoLottoCount)

        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        val lottoTickets = manualLottoTicket + autoLottoTicket
        outputView.printLottoTickets(lottoTickets)

        val lottoWinning = makeLottoWinning()
        val lottoResult = lottoWinning.makeLottoResult(lottoTickets)
        val profitRate = lottoResult.winningMoney / purchasePrice ?: 0f
        printWinningResult(lottoResult, profitRate)
    }

    private fun getLottoCount(purchasePrice: Money): Int {
        val lottoPurchase = LottoPurchase(Money(1000))
        val lottoCount = lottoPurchase.calculateLottoCount(purchasePrice)
        return lottoCount
    }

    private fun askUserForManualLottoTickets(count: Int): List<LottoTicket> =
        inputView.getManualLottoTickets(count)

    private fun generateAutoLottoTickets(count: Int): List<LottoTicket> {
        val lottoTicketFactory = LottoTicketFactory(RandomLottoTicketGenerator)
        val lottoTickets = lottoTicketFactory.makeLottoTickets(count)
        return lottoTickets
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
