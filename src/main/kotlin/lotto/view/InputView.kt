package lotto.view

object InputView {
    fun readInputMoney() = readInputNumber()

    fun readInputManualLottoCount() = readInputNumber()

    fun readInputBonusNumber() = readInputNumber()

    private fun readInputNumber(): Int? {
        val input = readln()
        return input.toIntOrNull()
    }

    fun readManualLottoNumbers() = readInputNumbers()

    fun readInputWinningLotto() = readInputNumbers()

    private fun readInputNumbers(): List<Int?> {
        val input = readln()
        return input.split(",").map { it.toIntOrNull() }
    }
}
