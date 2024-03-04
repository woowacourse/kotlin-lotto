package lotto.controller

import lotto.model.AutoLottoCountCalculator
import lotto.model.AutoLottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoWinningRank
import lotto.model.ManualLottoCount
import lotto.model.ManualLottoMachine
import lotto.model.Price
import lotto.model.UserLottoTicket
import lotto.model.WinningTable
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLotto() {
        val purchasePrice = Price(inputView.getPurchasePrice())
        val manualLottoCount = ManualLottoCount(inputView.getManualLottoCount())
        val manualTickets = makeManualTickets(manualLottoCount.num)
        val autoLottoCount = calculateAutoLottoCount(purchasePrice.num, manualLottoCount.num)
        val userTickets = makeAutoLottoTickets(autoLottoCount, manualTickets)
        outputView.printLottoCount(manualLottoCount.num, autoLottoCount)
        outputView.printUserTickets(userTickets)
        printLottoWinning(userTickets, purchasePrice.num)
    }

    private fun makeManualTickets(manualLottoCount: Int): List<UserLottoTicket> {
        val manualLottoNumbers = inputView.getManualLottoTickets(manualLottoCount)
        val manualLottoMachine = ManualLottoMachine(manualLottoNumbers)
        val manualTickets = manualLottoMachine.make()
        return manualTickets
    }

    private fun calculateAutoLottoCount(
        purchasePrice: Int,
        manualLottoCount: Int,
    ): Int {
        val autoLottoCountCalculator = AutoLottoCountCalculator(purchasePrice, manualLottoCount)
        val autoLottoCount = autoLottoCountCalculator.calculate()
        return autoLottoCount
    }

    private fun makeAutoLottoTickets(
        autoLottoCount: Int,
        manualTickets: List<UserLottoTicket>,
    ): List<UserLottoTicket> {
        val autoLottoMachine = AutoLottoMachine(autoLottoCount)
        val autoTickets = autoLottoMachine.make()
        val userTickets = manualTickets + autoTickets
        return userTickets
    }

    private fun printLottoWinning(
        purchasePrice: Int,
        winningTable: WinningTable,
    ) {
        val winningRate = winningTable.calculateWinningRate(purchasePrice)
        outputView.printWinningChart(winningTable)
        outputView.printWinningRate(winningRate)
    }

    private fun printLottoWinning(
        userTickets: List<UserLottoTicket>,
        purchasePrice: Int,
    ) {
        val winningTable = checkLottoWinning(userTickets)
        printLottoWinning(purchasePrice, winningTable)
    }

    private fun checkLottoWinning(userTickets: List<UserLottoTicket>): WinningTable {
        val winningNumbers = inputView.getWinningTicket().map { LottoNumber.of(it) }
        val bonusNumber = inputView.getBonusNumber()
        val lottoWinningRank =
            LottoWinningRank(winningNumbers, LottoNumber.of(bonusNumber))
        return lottoWinningRank.makeWinningTable(userTickets)
    }
}
