package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.PurchaseInfo
import lotto.view.ManualLottoInputView
import lotto.view.OutputView
import lotto.view.PurchaseInfoInputView
import lotto.view.WinningLottoInputView

class LottoController(
    private val purchaseInfoInputView: PurchaseInfoInputView = PurchaseInfoInputView(),
    private val manualLottoInputView: ManualLottoInputView = ManualLottoInputView(),
    private val winningLottoInputView: WinningLottoInputView = WinningLottoInputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val lottoCount = purchaseInfoInputView.readLottoCount()
        val lottoMachine = LottoMachine(lottoCount)

        val manualLottoNumbers = manualLottoInputView.readManualLottoNumbers(lottoCount.manualCount)
        val lottos = lottoMachine.issueLottos(manualLottoNumbers)
        outputView.printPurchaseLotto(lottoCount, lottos)

        printResult(lottos, lottoCount)
    }

    private fun printResult(
        lottos: List<Lotto>,
        purchaseInfo: PurchaseInfo,
    ) {
        val winningLotto = winningLottoInputView.readWinningLotto()

        outputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        outputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, purchaseInfo.purchasePrice))
    }
}
