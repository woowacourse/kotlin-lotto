package lotto.controller

import lotto.model.AutoLottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoWinningPrize
import lotto.model.LottoWinningRank
import lotto.model.ManualLottoMachine
import lotto.model.Rank
import lotto.model.UserLottoTicket
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLotto() {
        val userTickets = purchaseLottoTickets()
        val rankMap = checkLottoWinning(userTickets)
        val lottoWinningPrize = LottoWinningPrize(rankMap)
        printLottoWinning(lottoWinningPrize, userTickets, rankMap)
    }

    private fun purchaseLottoTickets(): List<UserLottoTicket> {
        val purchasePrice = inputView.getPurchasePrice()
        val manualLottoNumbers = inputView.getManualLottoTickets()
        val autoTickets = AutoLottoMachine(purchasePrice, manualLottoNumbers.size).makeAutoTickets()
        val manualTickets = ManualLottoMachine(manualLottoNumbers).makeManualTickets()
        val userTickets = autoTickets + manualTickets
        outputView.printLottoCount(manualLottoNumbers.size, autoTickets.size)
        outputView.printUserTickets(userTickets)
        return userTickets
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
