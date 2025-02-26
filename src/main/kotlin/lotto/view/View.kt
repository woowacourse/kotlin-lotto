package lotto.view

class View {
    val inputView = InputView(onInvalidInput = ::println)
    val outputView = OutputView()

    fun readPay(): Int {
        outputView.requestPrice()
        val price: Int = inputView.readNumber()
        return price
    }

    fun readManualLottoCount(): Int {
        outputView.requestManualLottoCount()
        return inputView.readNumber()
    }

    fun readManualLottosNumbers(size: Int): List<List<Int>> {
        if (size == 0) return emptyList()
        outputView.requestManualLottosNumbers()
        val manualLottoNumbers: List<List<Int>> = inputView.readLottosNumbers(size)
        return manualLottoNumbers
    }

    fun showLottoCount(
        manual: Int,
        random: Int,
    ) {
        outputView.showLottoCount(manual, random)
    }

    fun showLottos(numbers: List<List<Int>>) {
        outputView.showLottos(numbers)
    }

    fun showResult(
        lottoResults: List<String>,
        profitRate: Double,
    ) {
        outputView.showResult(lottoResults, profitRate)
    }

    fun readLottoNumbers(): List<Int> {
        outputView.requestWinningLotto()
        val winningNumbers: List<Int> = inputView.readNumbers()
        return winningNumbers
    }

    fun readBonusNumber(): Int {
        outputView.requestBonusNumber()
        return inputView.readNumber()
    }

    fun showError(error: Throwable) {
        println(error.message)
    }
}
