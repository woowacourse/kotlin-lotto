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
        val manualLottoTickets = LottoMachine.issueManualTickets(manualLottoNumbers)

        val numberOfAutoTickets = numberOfTotalTickets - numberOfManualTickets
        val autoLottoTickets =
            LottoMachine.issueAutoTickets(numberOfAutoTickets, LottoNumbersGenerator)

        val totalLottoTickets = manualLottoTickets.plus(autoLottoTickets)
        OutputView.printNumberOfTicket(numberOfManualTickets, numberOfAutoTickets)
        OutputView.printLottoTickets(totalLottoTickets)

        makeResult(totalLottoTickets)
    }

    private fun makeResult(lottoTickets: List<Lotto>) {
        val lotteryResult =
            WinningLotto(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        val winningStatus = WinningStatus(lotteryResult.generateWinningStatus(lottoTickets))

        OutputView.printWinningStatus(winningStatus)
        OutputView.printEarningRate(winningStatus.getEarningRate())
    }
}
