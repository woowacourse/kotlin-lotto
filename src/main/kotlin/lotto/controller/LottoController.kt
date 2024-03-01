package lotto.controller

import lotto.model.PurchaseCountInfo
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
        val manualLottoNumbers = inputView.readManualLottoNumbers(ticket.purchaseCountInfo.manualCount)
        val lottos = ticket.issueLottos(manualLottoNumbers)
        outputView.printPurchaseLotto(ticket.purchaseCountInfo, lottos)

        val winningLotto = readWinningLotto()

        outputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        outputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, ticket.purchaseCountInfo.purchasePrice))
    }

    private fun issueTicket(): Ticket {
        val purchasePrice = inputView.readPurchasePrice()
        val manualLottoCount = inputView.readManualLottoCount()

        val purchaseCountInfo = PurchaseCountInfo(purchasePrice, 1_000, manualLottoCount)

        return Ticket(purchaseCountInfo)
    }

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        return WinningLotto(winningLottoNumbers, bonusNumber)
    }
}
