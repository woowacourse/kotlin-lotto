package lotto.controller

import lotto.model.LottoCountCalculator
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.MAX_LOTTO_NUMBER
import lotto.model.LottoNumber.Companion.MIN_LOTTO_NUMBER
import lotto.model.LottoWinningPrize
import lotto.model.LottoWinningRank
import lotto.model.UserLottoTicket
import lotto.model.WinningTable
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()
    private val lottoCountCalculator = LottoCountCalculator()

    fun runLotto() {
        val purchasePrice = inputView.getPurchasePrice()
        val manualLottoNumbers = inputView.getManualLottoTickets()
        val autoLottoCount = lottoCountCalculator.calculate(purchasePrice, manualLottoNumbers)
        val randomNumbers =
            List(autoLottoCount) {
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

    private fun checkLottoWinning(userTickets: List<UserLottoTicket>): WinningTable {
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinningRank = LottoWinningRank(winningNumbers, LottoNumber(bonusNumber))
        return lottoWinningRank.makeRankMap(userTickets)
    }

    private fun printLottoWinning(
        lottoWinningPrize: LottoWinningPrize,
        userTickets: List<UserLottoTicket>,
        winningTable: WinningTable,
    ) {
        val winningRate = lottoWinningPrize.calculateWinningRate(userTickets.size)
        outputView.printWinningChart(winningTable)
        outputView.printWinningRate(winningRate)
    }
}
