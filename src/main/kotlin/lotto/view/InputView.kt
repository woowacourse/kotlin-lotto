package lotto.view

object InputView {
    fun readInputMoney() = readInputNumber()

    fun readInputManualLottoCount() = readInputNumber()

    fun readInputBonusNumber() = readInputNumber()

    private fun readInputNumber(): String {
        return readln()
    }

    fun readManualLottoNumbers() = readInputNumbers()

    fun readInputWinningLotto() = readInputNumbers()

    private fun readInputNumbers(): List<String> {
        val input = readln()
        return input.split(",")
    }
}
