package lotto.controller

import lotto.model.AutoLottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoWinningPrize
import lotto.model.LottoWinningRank
import lotto.model.Rank
import lotto.model.UserLottoTicket
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLotto() {
        val userTickets = makeUserLottoTickets()
        printLottoTickets(userTickets)
        val rankMap = checkLottoWinning(userTickets)
        val lottoWinningPrize = LottoWinningPrize(rankMap)
        printLottoWinning(lottoWinningPrize, userTickets, rankMap)
    }

    private fun makeUserLottoTickets(): List<UserLottoTicket> {
        val purchasePrice = inputView.getPurchasePrice()
        val autoLottoMachine = AutoLottoMachine(purchasePrice)
        return autoLottoMachine.makeUserTickets()
    }

    private fun printLottoTickets(userTickets: List<UserLottoTicket>) {
        outputView.printLottoCount(userTickets.size)
        outputView.printLottoTickets(userTickets)
    }

    private fun checkLottoWinning(userTickets: List<UserLottoTicket>): Map<Rank, Int> {
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinningRank = LottoWinningRank(winningNumbers, LottoNumber(bonusNumber))
        return lottoWinningRank.makeRankMap(userTickets)
    }

    private fun printLottoWinning(
        lottoWinningPrize: LottoWinningPrize,
        userTickets: List<UserLottoTicket>,
        rankMap: Map<Rank, Int>,
    ) {
        val winningRate = lottoWinningPrize.calculateWinningRate(userTickets.size)
        outputView.printWinningChart(rankMap)
        outputView.printWinningRate(winningRate)
    }
}
