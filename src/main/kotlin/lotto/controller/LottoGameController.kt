package lotto.controller

import lotto.model.LottoMachine
import lotto.model.WinningStatusChecker
import lotto.util.WinningRank
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = InputView.getPurchaseAmount()
        val numberOfTicket = LottoMachine.getNumberOfTicket(purchaseAmount)
        val lottoTickets = LottoMachine.issueTickets(numberOfTicket)

        OutputView.printNumberOfTicket(numberOfTicket)
        OutputView.printLottoTickets(lottoTickets)

        val winningNumbers = InputView.getWinningNumbers()
        val bonusNumber = InputView.getBonusNumber()

        val winningResult = lottoTickets.map {
            WinningRank.convert(
                it.checkWinningNumbers(winningNumbers),
                it.checkBonusNumbers(bonusNumber)
            )
        }
        val winningStatusChecker = WinningStatusChecker(winningResult)

        OutputView.printEarningRate(winningStatusChecker.getEarningRate())
    }
}
