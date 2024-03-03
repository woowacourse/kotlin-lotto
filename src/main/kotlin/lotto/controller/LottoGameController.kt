package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.NumberOfManual
import lotto.model.WinningLotto
import lotto.model.WinningResult
import lotto.model.WinningStatusChecker
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val lottoTickets = getLottoTickets()

        OutputView.printNumberOfTicket(lottoTickets.size)
        OutputView.printLottoTickets(lottoTickets)

        makeResult(lottoTickets)
    }

    private fun getLottoTickets(): List<Lotto> {
        val lottoMachine =
            LottoMachine.create(
                InputView.getPurchaseAmount(),
                InputView.getManualTicketCounts(),
            )
        return if (lottoMachine.ticketCounts.numberOfManual.counts != NumberOfManual.MIN_NUMBER_OF_MANUAL) {
            lottoMachine.issueTickets(InputView.getManualLotto(lottoMachine.ticketCounts.numberOfManual.counts))
        } else {
            lottoMachine.issueTickets()
        }
    }

    private fun makeResult(lottoTickets: List<Lotto>) {
        val winningLotto = WinningLotto(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        val winningResult = WinningResult.create(winningLotto, lottoTickets)
        val winningStatusChecker = WinningStatusChecker(winningResult.result)

        OutputView.printWinningStatus(winningStatusChecker)
        OutputView.printEarningRate(winningStatusChecker.getEarningRate())
    }
}
