package lotto.controller

import lotto.model.LottoNumber
import lotto.model.LottoPurchase
import lotto.model.LottoWinning
import lotto.model.UserLottoTicket
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLotto() {
        val userTickets = makeUserLottoTickets()
        printLottoTickets(userTickets)
        val lottoWinning = checkLottoWinning(userTickets)
        printLottoWinning(lottoWinning)
    }

    private fun printLottoWinning(lottoWinning: LottoWinning) {
        val rankMap = lottoWinning.makeRankMap()
        val winningRate = lottoWinning.calculateWinningRate()
        outputView.printWinningChart(rankMap)
        outputView.printWinningRate(winningRate)
    }

    private fun checkLottoWinning(userTickets: List<UserLottoTicket>): LottoWinning {
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinning = LottoWinning(userTickets, winningNumbers, LottoNumber(bonusNumber))
        return lottoWinning
    }

    private fun printLottoTickets(userTickets: List<UserLottoTicket>) {
        outputView.printLottoCount(userTickets.size)
        outputView.printLottoTickets(userTickets)
    }

    private fun makeUserLottoTickets(): List<UserLottoTicket> {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoPurchase = LottoPurchase(purchasePrice)
        return lottoPurchase.makeUserTickets()
    }
}
