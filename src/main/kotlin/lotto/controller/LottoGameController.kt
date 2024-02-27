package lotto.controller

import lotto.model.LotteryResult
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.NumberOfManual
import lotto.model.NumberOfTickets
import lotto.model.WinningRank
import lotto.model.WinningStatusChecker
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = InputView.getPurchaseAmount()
        val numberOfTicket = NumberOfTickets(purchaseAmount)
        val numberOfManual = NumberOfManual(0, numberOfTicket.counts)
        val lottoMachine = LottoMachine(numberOfTicket, numberOfManual)
        val lottoTickets = lottoMachine.issueTickets(numberOfTicket.counts)

        OutputView.printNumberOfTicket(numberOfTicket.counts)
        OutputView.printLottoTickets(lottoTickets)

        makeResult(lottoTickets)
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
