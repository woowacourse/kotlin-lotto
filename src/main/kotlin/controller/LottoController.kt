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
        val lottoTickets = purchaseLottoTickets(purchasePrice, RandomLottoTicketGenerator)
        val lottoWinning = makeLottoWinning()
        val lottoResult = lottoWinning.makeLottoResult(lottoTickets)
        val profitRate = lottoResult.winningMoney.toFloat() / purchasePrice
        printWinningResult(lottoResult, profitRate)
    }

    private fun purchaseLottoTickets(
        purchasePrice: Int,
        lottoTicketGenerator: LottoTicketGenerator,
    ): List<LottoTicket> {
        val lottoPurchase = LottoPurchase(purchasePrice, lottoTicketGenerator)
        val lottoCount = lottoPurchase.lottoCount
        val lottoTickets = lottoPurchase.makeUserTickets()
        outputView.printLottoCount(lottoCount)
        outputView.printLottoTickets(lottoTickets)
        return lottoTickets
    }

    private fun makeLottoWinning(): LottoWinning {
        val winningTicket = LottoTicket(inputView.getWinningTicket().map { LottoNumber(it) })
        val bonusNumber = LottoNumber(inputView.getBonusNumber())
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
