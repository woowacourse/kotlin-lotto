package lotto.view

import lotto.domain.LottoResult
import lotto.domain.Lottos

object View {
    fun readPrice(): Int {
        OutputView.requestPrice()
        val price: Int = InputView.readPrice()
        return price
    }

    fun readManualQuantity(): Int {
        OutputView.requestManualQuantity()
        val manualQuantity: Int = InputView.readManualQuantity()
        return manualQuantity
    }

    fun showLottoCount(lottoCount: Int) {
        OutputView.showLottoCount(lottoCount)
    }

    fun showLottos(lottos: Lottos) {
        OutputView.showLottos(lottos)
    }

    fun readLottoNumbers(): List<Int> {
        OutputView.requestWinningLotto()
        val winningNumbers: List<Int> = InputView.readWinningNumbers()
        return winningNumbers
    }

    fun readBonusNumber(): Int {
        OutputView.requestBonusNumber()
        return InputView.readBonusNumber()
    }

    fun showResult(
        resultTally: Map<LottoResult, Int>,
        profitRate: Double,
    ) {
        OutputView.showResult(resultTally, profitRate)
    }
}
