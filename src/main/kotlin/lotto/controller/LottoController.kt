package lotto.controller

import lotto.model.AutoLottoCountCalculator
import lotto.model.AutoLottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoWinningRank
import lotto.model.ManualLottoMachine
import lotto.model.UserLottoTicket
import lotto.model.WinningTable
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLotto() {
        val purchasePrice = inputView.getPurchasePrice()
        val manualLottoCount = inputView.getManualLottoCount()
        val manualLottoNumbers = inputView.getManualLottoTickets(manualLottoCount)
        val autoLottoCountCalculator = AutoLottoCountCalculator(purchasePrice, manualLottoCount)
        val autoLottoCount = autoLottoCountCalculator.calculate()

        val autoLottoMachine = AutoLottoMachine(autoLottoCount)
        val autoTickets = autoLottoMachine.make()

        val manualLottoMachine = ManualLottoMachine(manualLottoNumbers)
        val manualTickets = manualLottoMachine.make()

        val userTickets = manualTickets + autoTickets
        outputView.printLottoCount(manualLottoCount, autoLottoCount)
        outputView.printUserTickets(userTickets)

        val winningTable = checkLottoWinning(userTickets)
        printLottoWinning(purchasePrice, winningTable)
    }

    private fun checkLottoWinning(userTickets: List<UserLottoTicket>): WinningTable {
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinningRank = LottoWinningRank(winningNumbers, LottoNumber(bonusNumber))
        return lottoWinningRank.makeRankMap(userTickets)
    }

    private fun printLottoWinning(
        purchasePrice: Int,
        winningTable: WinningTable,
    ) {
        val winningRate = winningTable.calculateWinningRate(purchasePrice)
        outputView.printWinningChart(winningTable)
        outputView.printWinningRate(winningRate)
    }
}
