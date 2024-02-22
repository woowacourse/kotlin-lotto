package controller

import model.LottoPurchase
import model.LottoTicket
import model.LottoWinning
import view.InputView
import view.OutputView

class LottoController {
    val inputView = InputView()
    val outputView = OutputView()

    fun run() {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoPurchase = LottoPurchase(purchasePrice)
        val lottoCount = lottoPurchase.makeLottoCount()
        val lottoTickets = lottoPurchase.makeUserTickets()

        outputView.printLottoCount(lottoCount)
        outputView.printLottoTickets(lottoTickets)

        val winningTicket = LottoTicket(inputView.getWinningTicket())
        val bonusNumber = inputView.getBonusNumber()

        val lottoWinning = LottoWinning(winningTicket,bonusNumber,lottoTickets)
        val rankList = lottoWinning.getRankList()
        val winningChart = lottoWinning.makeWinningChart(rankList)
        val winningRate = lottoWinning.calculateWinningRate(lottoCount)

        outputView.printWinningChart(winningChart)
        outputView.printWinningRate(winningRate)
    }

}