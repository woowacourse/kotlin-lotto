package lotto.controller

import lotto.model.LottoMachine
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
    }
}
