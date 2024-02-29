package lotto.controller

import lotto.model.Ticket
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val ticket = issueTicket()
        val lottos = ticket.issueLottos()
        outputView.printPurchaseLotto(lottos)

        val winningLotto = readWinningLotto()

        outputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        outputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, ticket.purchasePrice))
    }

    private fun issueTicket(): Ticket {
        val purchasePrice = inputView.readPurchasePrice()
        return Ticket(purchasePrice, 1_000)
    }

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        return WinningLotto(winningLottoNumbers, bonusNumber)
    }
}
