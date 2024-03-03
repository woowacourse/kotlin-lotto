package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.PurchaseInfo
import lotto.view.ManualLottoInputView
import lotto.view.OutputView
import lotto.view.PurchaseInfoInputView
import lotto.view.WinningLottoInputView

class LottoController {
    fun run() {
        val lottoCount = PurchaseInfoInputView.readLottoCount()
        val lottoMachine = LottoMachine(lottoCount)

        val manualLottoNumbers = ManualLottoInputView.readManualLottoNumbers(lottoCount.manualCount)
        val lottos = lottoMachine.issueLottos(manualLottoNumbers)
        OutputView.printPurchaseLotto(lottoCount, lottos)

        printResult(lottos, lottoCount)
    }

    private fun printResult(
        lottos: List<Lotto>,
        purchaseInfo: PurchaseInfo,
    ) {
        val winningLotto = WinningLottoInputView.readWinningLotto()

        OutputView.printWinningResult(winningLotto.calculatePrizeCount(lottos))
        OutputView.printWinningRatio(winningLotto.calculateProfitRatio(lottos, purchaseInfo.purchasePrice))
    }
}
