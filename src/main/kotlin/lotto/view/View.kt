package lotto.view

import lotto.domain.Lotto
import lotto.domain.Results

object View {
    fun requestPayment(): Int {
        OutputView.requestPayment()
        return retryUntilValid { InputView.readPayment() }
    }

    fun requestManualQuantity(): Int {
        OutputView.requestManualQuantity()
        return retryUntilValid { InputView.readManualQuantity() }
    }

    fun requestManualNumbers() {
        OutputView.requestManualNumbers()
    }

    fun readManualNumbers(): List<Int> {
        return retryUntilValid { InputView.readLottoNumbers() }
    }

    fun requestWinningNumbers(): List<Int> {
        OutputView.requestWinningLotto()
        return retryUntilValid { InputView.readLottoNumbers() }
    }

    fun requestBonusNumber(): Int {
        OutputView.requestBonusNumber()
        return retryUntilValid { InputView.readBonusNumber() }
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

    fun showResult(results: Results) {
        OutputView.showResults(results)
    }

    private fun <T> retryUntilValid(reader: () -> T?): T {
        return reader() ?: run {
            OutputView.warnNonNumericInput()
            retryUntilValid(reader)
        }
    }
}
