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
        val lottoWinning = checkLottoWinning()
        printLottoWinning(lottoWinning, userTickets)
    }

    private fun makeUserLottoTickets(): List<UserLottoTicket> {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoPurchase = LottoPurchase(purchasePrice)
        return lottoPurchase.makeUserTickets()
    }

    private fun printLottoTickets(userTickets: List<UserLottoTicket>) {
        outputView.printLottoCount(userTickets.size)
        outputView.printLottoTickets(userTickets)
    }

    private fun checkLottoWinning(): LottoWinning {
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinning = LottoWinning(winningNumbers, LottoNumber(bonusNumber))
        return lottoWinning
    }

    private fun printLottoWinning(
        lottoWinning: LottoWinning,
        userTickets: List<UserLottoTicket>,
    ) {
        val rankMap = lottoWinning.makeRankMap(userTickets)
        val winningRate = lottoWinning.calculateWinningRate(userTickets)
        outputView.printWinningChart(rankMap)
        outputView.printWinningRate(winningRate)
    }
}
