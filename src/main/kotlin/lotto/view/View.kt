package lotto.view

import lotto.domain.Lotto
import lotto.domain.Results

object View {
    fun readPayment(): Int {
        OutputView.requestPayment()
        val payment: Int = InputView.readPayment()
        return payment
    }

    fun readManualQuantity(): Int {
        OutputView.requestManualQuantity()
        val manualQuantity: Int = InputView.readManualQuantity()
        return manualQuantity
    }

    fun requestManualNumbers() {
        OutputView.requestManualNumbers()
    }

    fun readManualNumbers(): List<Int> {
        return InputView.readLottoNumbers()
    }

    fun showLottoCount(
        manualQuantity: Int,
        automaticQuantity: Int,
    ) {
        OutputView.showLottoCount(manualQuantity, automaticQuantity)
    }

    fun showLottos(vararg allLottos: List<Lotto>) {
        allLottos.forEach { lottos: List<Lotto> ->
            OutputView.showLottos(lottos)
        }
    }

    fun readWinningNumbers(): List<Int> {
        OutputView.requestWinningLotto()
        val winningNumbers: List<Int> = InputView.readLottoNumbers()
        return winningNumbers
    }

    fun readBonusNumber(): Int {
        OutputView.requestBonusNumber()
        return InputView.readBonusNumber()
    }

    fun showResult(results: Results) {
        OutputView.showResults(results)
    }
}
