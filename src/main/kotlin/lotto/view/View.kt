package lotto.view

import lotto.domain.Lotto
import lotto.domain.Results

object View {
    fun requestPayment(): Int {
        OutputView.requestPayment()
        return readPayment()
    }

    fun requestManualQuantity(): Int {
        OutputView.requestManualQuantity()
        return readManualQuantity()
    }

    fun requestManualNumbers() {
        OutputView.requestManualNumbers()
    }

    fun readManualNumbers(): List<Int> {
        val lottoNumbers: List<Int>? = InputView.readLottoNumbers()
        if (lottoNumbers == null) {
            OutputView.warnNonNumericInput()
            return readManualNumbers()
        }
        return lottoNumbers
    }

    fun requestWinningNumbers(): List<Int> {
        OutputView.requestWinningLotto()
        return readWinningNumbers()
    }

    fun requestBonusNumber(): Int {
        OutputView.requestBonusNumber()
        return readBonusNumber()
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

    private fun readPayment(): Int {
        val payment: Int? = InputView.readPayment()
        if (payment == null) {
            OutputView.warnNonNumericInput()
            return readPayment()
        }
        return payment
    }

    private fun readManualQuantity(): Int {
        val manualQuantity: Int? = InputView.readManualQuantity()
        if (manualQuantity == null) {
            OutputView.warnNonNumericInput()
            return readManualQuantity()
        }
        return manualQuantity
    }

    private fun readWinningNumbers(): List<Int> {
        val winningNumbers: List<Int>? = InputView.readLottoNumbers()
        if (winningNumbers == null) {
            OutputView.warnNonNumericInput()
            return readWinningNumbers()
        }
        return winningNumbers
    }

    private fun readBonusNumber(): Int {
        val bonusNumber: Int? = InputView.readBonusNumber()
        if (bonusNumber == null) {
            OutputView.warnNonNumericInput()
            return readBonusNumber()
        }
        return bonusNumber
    }
}
