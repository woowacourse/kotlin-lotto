package lotto.view

class View(
    val outputView: OutputView = OutputView(),
    val inputView: InputView = InputView(onInvalidInput = outputView::showError),
) {
    fun readPay(): Int {
        outputView.requestPrice()
        var price: Int? = inputView.readNumber()
        while (price == null) {
            outputView.showError(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
            price = inputView.readNumber()
        }
        return price
    }

    fun readManualLottoCount(): Int {
        outputView.requestManualLottoCount()
        var manualLottoCount: Int? = inputView.readNumber()
        while (manualLottoCount == null) {
            outputView.showError(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
            manualLottoCount = inputView.readNumber()
        }

        return manualLottoCount
    }

    fun readManualLottosNumbers(size: Int): List<List<Int>> {
        if (size == 0) return emptyList()
        outputView.requestManualLottosNumbers()
        val manualLottoNumbers: List<List<Int>>? = inputView.readLottosNumbers(size)
        while (manualLottoNumbers == null) {
            outputView.showError(ERROR_MESSAGE_INVALID_LOTTO_NUMBERS)
        }
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
        var winningNumbers: List<Int>? = inputView.readNumbers()
        while (winningNumbers == null) {
            outputView.showError(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
            inputView.readNumbers()
        }
        return winningNumbers
    }

    fun readBonusNumber(): Int {
        outputView.requestBonusNumber()
        var bonusNumber: Int? = inputView.readNumber()
        while (bonusNumber == null) {
            outputView.showError(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
            bonusNumber = inputView.readNumber()
        }
        return bonusNumber
    }

    fun showError(error: Throwable) {
        outputView.showError(error.message)
    }

    companion object {
        private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
        private const val ERROR_MESSAGE_INVALID_LOTTO_NUMBERS = "양식에 맞게 입력해주세요. 예시: 1, 2, 3, 4, 5, 6"
    }
}
