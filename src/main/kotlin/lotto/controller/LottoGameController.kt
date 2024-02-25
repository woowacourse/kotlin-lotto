package lotto.controller

import lotto.model.LotteryResultAnalyzer
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.WinningStatus
import lotto.util.LottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = InputView.getPurchaseAmount()
        val numberOfTicket = LottoMachine.getNumberOfTicket(purchaseAmount)
        val lottoTickets = LottoMachine.issueTickets(numberOfTicket, LottoNumbersGenerator)

        OutputView.printNumberOfTicket(numberOfTicket)
        OutputView.printLottoTickets(lottoTickets)

        makeResult(lottoTickets)
    }

    private fun makeResult(lottoTickets: List<Lotto>) {
        val lotteryResult =
            LotteryResultAnalyzer(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        val winningStatus = WinningStatus(lotteryResult.generateWinningStatus(lottoTickets))

        OutputView.printWinningStatus(winningStatus)
        OutputView.printEarningRate(winningStatus.getEarningRate())
    }
}
