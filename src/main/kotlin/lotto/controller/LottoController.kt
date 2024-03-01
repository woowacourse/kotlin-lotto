package lotto.controller

import lotto.model.LottoCount
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
        val manualLottoCount = inputView.readManualLottoCount()

        val lottoCount = LottoCount(purchasePrice, 1_000, manualLottoCount)
        val ticket = Ticket(lottoCount)

        val manualLottoNumbers = inputView.readManualLottoNumbers(lottoCount.manualCount)
        val lottos = ticket.issueLottos(manualLottoNumbers)
        outputView.printPurchaseLotto(lottoCount, lottos)

        val winningLotto = readWinningLotto()

        outputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        outputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, lottoCount.purchasePrice))
    }

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = inputView.readWinningLottoNumbers()
        val bonusNumber = inputView.readBonusNumber()

        return WinningLotto(winningLottoNumbers, bonusNumber)
    }
}
