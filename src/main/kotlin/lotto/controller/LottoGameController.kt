package lotto.controller

import lotto.model.LotteryResult
import lotto.model.Lotto
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

        OutputView.printWinningStatus(winningStatusChecker.toString())
        OutputView.printEarningRate(winningStatusChecker.getEarningRate())
    }
}
