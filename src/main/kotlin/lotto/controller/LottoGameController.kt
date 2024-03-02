package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.NumberOfManual
import lotto.model.NumberOfTickets
import lotto.model.TicketCounts
import lotto.model.WinningLotto
import lotto.model.WinningRank
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
        val purchaseAmount = InputView.getPurchaseAmount()
        val getManualCounts = InputView.getManualTicketCounts()
        val ticketCounts = TicketCounts(NumberOfTickets(purchaseAmount), NumberOfManual(getManualCounts))
        val lottoMachine = LottoMachine(ticketCounts)
        return if (lottoMachine.ticketCounts.numberOfManual.counts != NumberOfManual.MIN_NUMBER_OF_MANUAL) {
            lottoMachine.issueTickets(InputView.getManualLotto(ticketCounts.numberOfManual.counts))
        } else {
            lottoMachine.issueTickets()
        }
    }

    private fun makeResult(lottoTickets: List<Lotto>) {
        val winningLotto = WinningLotto(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        val winningResult =
            lottoTickets
                .map {
                    WinningRank.convert(
                        it.checkWinningNumbers(winningLotto.winning),
                        it.checkBonusNumbers(winningLotto.bonusNumber),
                    )
                }
        val winningStatusChecker = WinningStatusChecker(winningResult)

        OutputView.printWinningStatus(winningStatusChecker)
        OutputView.printEarningRate(winningStatusChecker.getEarningRate())
    }
}
