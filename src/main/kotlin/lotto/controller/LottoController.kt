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
        val purchasePrice = inputView.readPurchasePrice()
        val ticket = Ticket(purchasePrice, 1_000)
        val lottos = ticket.issueLottos()
        outputView.printPurchaseLotto(lottos)

        val winningLottoNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        val winningLotto = WinningLotto(winningLottoNumbers, bonusNumber)

        outputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        outputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, ticket.purchasePrice))
    }
}
