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
        val lottoTickets = purchaseLottoTickets(purchasePrice)
        val (winningTicket, bonusNumber) = makeWinningTicket()
        val lottoResult = checkWinning(winningTicket, bonusNumber, lottoTickets)
        val profitRate = lottoResult.winningMoney.toFloat() / purchasePrice
        printWinningResult(lottoResult, profitRate)
    }

    private fun purchaseLottoTickets(purchasePrice: Int): List<LottoTicket> {
        val lottoPurchase = LottoPurchase(purchasePrice, RandomLottoTicketGenerator)
        val lottoCount = lottoPurchase.lottoCount
        val lottoTickets = lottoPurchase.makeUserTickets()
        outputView.printLottoCount(lottoCount)
        outputView.printLottoTickets(lottoTickets)
        return lottoTickets
    }

    private fun makeWinningTicket(): Pair<LottoTicket, LottoNumber> {
        val winningTicket = LottoTicket(inputView.getWinningTicket().map { LottoNumber(it) })
        val bonusNumber = LottoNumber(inputView.getBonusNumber())
        return Pair(winningTicket, bonusNumber)
    }

    private fun checkWinning(
        winningTicket: LottoTicket,
        bonusNumber: LottoNumber,
        lottoTickets: List<LottoTicket>,
    ): LottoResult {
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, lottoTickets)
        return lottoWinning.makeLottoResult()
    }

    private fun printWinningResult(
        lottoResult: LottoResult,
        profitRate: Float,
    ) {
        outputView.printWinningChart(lottoResult)
        outputView.printWinningRate(profitRate)
    }
}
