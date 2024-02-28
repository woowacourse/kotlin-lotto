package lotto.controller

import lotto.model.LotteryResult
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.NumberOfManual
import lotto.model.NumberOfTickets
import lotto.model.TicketCounts
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
        var manualLotto = listOf<Lotto>()
        if (lottoMachine.ticketCounts.numberOfManual.counts != NumberOfManual.MIN_NUMBER_OF_MANUAL) {
            manualLotto = InputView.getManualLotto(ticketCounts.numberOfManual.counts)
        }

        return lottoMachine.issueTickets(manualLotto)
    }

    private fun makeResult(lottoTickets: List<Lotto>) {
        val lotteryResult = LotteryResult(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        val winningResult =
            lottoTickets
                .map {
                    WinningRank.convert(
                        it.checkWinningNumbers(lotteryResult.winning),
                        it.checkBonusNumbers(lotteryResult.bonusNumber),
                    )
                }
        val winningStatusChecker = WinningStatusChecker(winningResult)

        OutputView.printWinningStatus(winningStatusChecker)
        OutputView.printEarningRate(winningStatusChecker.getEarningRate())
    }
}
