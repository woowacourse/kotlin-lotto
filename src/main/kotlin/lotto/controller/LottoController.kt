package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.Ticket
import lotto.view.LottoCountInputView
import lotto.view.ManualLottoInputView
import lotto.view.OutputView
import lotto.view.WinningLottoInputView

class LottoController(
    private val lottoCountInputView: LottoCountInputView = LottoCountInputView(),
    private val manualLottoInputView: ManualLottoInputView = ManualLottoInputView(),
    private val winningLottoInputView: WinningLottoInputView = WinningLottoInputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val lottoCount = lottoCountInputView.readLottoCount()
        val ticket = Ticket(lottoCount)

        val manualLottoNumbers = manualLottoInputView.readManualLottoNumbers(lottoCount.manualCount)
        val lottos = ticket.issueLottos(manualLottoNumbers)
        outputView.printPurchaseLotto(lottoCount, lottos)

        printResult(lottos, lottoCount)
    }

    private fun printResult(
        lottos: List<Lotto>,
        lottoCount: LottoCount,
    ) {
        val winningLotto = winningLottoInputView.readWinningLotto()

        outputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        outputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, lottoCount.purchasePrice))
    }
}
