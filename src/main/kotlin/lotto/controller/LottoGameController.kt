package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.WinningLotto
import lotto.model.WinningStatus
import lotto.util.LottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = InputView.getPurchaseAmount()
        val numberOfTotalTickets = LottoMachine.getNumberOfTicket(purchaseAmount)

        val numberOfManualTickets = InputView.getNumberOfManualLotto()
        val manualLottoNumbers = InputView.getManualLottoNumbers(numberOfManualTickets)

        val autoLottoTickets =
            LottoMachine.issueTickets(numberOfTotalTickets - numberOfManualTickets, LottoNumbersGenerator)

        OutputView.printNumberOfTicket(numberOfTotalTickets)
        OutputView.printLottoTickets(autoLottoTickets)

        makeResult(autoLottoTickets)
    }

    private fun makeResult(lottoTickets: List<Lotto>) {
        val lotteryResult =
            WinningLotto(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        val winningStatus = WinningStatus(lotteryResult.generateWinningStatus(lottoTickets))

        OutputView.printWinningStatus(winningStatus)
        OutputView.printEarningRate(winningStatus.getEarningRate())
    }
}
