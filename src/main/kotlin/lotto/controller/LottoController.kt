package lotto.controller

import lotto.model.LottoNumber
import lotto.model.LottoPurchase
import lotto.model.LottoWinning
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoPurchase = LottoPurchase(purchasePrice)
        val userTickets = lottoPurchase.makeUserTickets()
        outputView.printLottoCount(userTickets.size)
        outputView.printLottoTickets(userTickets)
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        val rankMap = lottoWinning.makeRankMap()
        val winningRate = lottoWinning.calculateWinningRate()
        outputView.printWinningChart(rankMap)
        outputView.printWinningRate(winningRate)
    }
}
