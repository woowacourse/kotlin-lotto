package lotto.view

object View {
    fun readPay(): Int {
        OutputView.requestPrice()
        val price: Int = InputView.readPrice()
        return price
    }

    fun readManualLottoCount(): Int {
        OutputView.requestManualLottoCount()
        return InputView.readNumber()
    }

    fun readManualLottosNumbers(size: Int): List<List<Int>> {
        if (size == 0) return emptyList()
        OutputView.requestManualLottosNumbers()
        val manualLottoNumbers: List<List<Int>> = InputView.readLottosNumbers(size)
        return manualLottoNumbers
    }

    fun showLottoCount(lottoCount: Int) {
        OutputView.showLottoCount(lottoCount)
    }

    fun showLottos(numbers: List<List<Int>>) {
        OutputView.showLottos(numbers)
    }

    fun showResult(
        lottoResults: List<String>,
        profitRate: Double,
    ) {
        OutputView.showResult(lottoResults, profitRate)
    }

    fun readLottoNumbers(): List<Int> {
        OutputView.requestWinningLotto()
        val winningNumbers: List<Int> = InputView.readWinningNumbers()
        return winningNumbers
    }

    fun readBonusNumber(): Int {
        OutputView.requestBonusNumber()
        return InputView.readNumber()
    }
}
