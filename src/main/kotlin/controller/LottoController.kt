package controller

import model.LottoPurchase
import model.LottoResult
import model.LottoTicket
import model.LottoWinning
import view.InputView
import view.OutputView

class LottoController {
    val inputView = InputView()
    val outputView = OutputView()

    fun run() {
        val lottoTickets = purchaseLottoTickets()
        val (winningTicket, bonusNumber) = makeWinningTicket()
        val (winningChart, winningRate) = checkWinning(winningTicket, bonusNumber, lottoTickets)
        printWinningResult(winningChart, winningRate)
    }

    private fun printWinningResult(winningChart: LottoResult, winningRate: Float) {
        outputView.printWinningChart(winningChart)
        outputView.printWinningRate(winningRate)
    }

    private fun checkWinning(
        winningTicket: LottoTicket,
        bonusNumber: Int,
        lottoTickets: List<LottoTicket>
    ): Pair<LottoResult, Float> {
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, lottoTickets)
        val winningChart = lottoWinning.makeWinningChart()
        val winningRate = lottoWinning.calculateWinningRate()
        return Pair(winningChart, winningRate)
    }

    private fun makeWinningTicket(): Pair<LottoTicket, Int> {
        val winningTicket = LottoTicket(inputView.getWinningTicket())
        val bonusNumber = inputView.getBonusNumber()
        return Pair(winningTicket, bonusNumber)
    }

    private fun purchaseLottoTickets(): List<LottoTicket> {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoPurchase = LottoPurchase(purchasePrice)
        val lottoCount = lottoPurchase.makeLottoCount()
        val lottoTickets = lottoPurchase.makeUserTickets()
        outputView.printLottoCount(lottoCount)
        outputView.printLottoTickets(lottoTickets)
        return lottoTickets
    }
}