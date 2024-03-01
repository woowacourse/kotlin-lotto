package lotto.controller

import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.MAX_LOTTO_NUMBER
import lotto.model.LottoNumber.Companion.MIN_LOTTO_NUMBER
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
        val purchasePrice = inputView.getPurchasePrice()
        val manualLottoNumbers = inputView.getManualLottoTickets()
        val randomNumbers =
            List(purchasePrice / 1000 - manualLottoNumbers.size) {
                (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).shuffled().take(6)
            }
        val lottoMachine = LottoMachine()
        val autoTickets = lottoMachine.make(randomNumbers)
        val manualTickets = lottoMachine.make(manualLottoNumbers)

        val userTickets = manualTickets + autoTickets
        outputView.printLottoCount(manualLottoNumbers.size, autoTickets.size)
        outputView.printUserTickets(userTickets)

        val rankMap = checkLottoWinning(userTickets)
        val lottoWinningPrize = LottoWinningPrize(rankMap)
        printLottoWinning(lottoWinningPrize, userTickets, rankMap)
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
