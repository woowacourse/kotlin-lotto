package lotto.view

object InputView {
    fun readInputMoney() = readIntOrNull()

    fun readInputManualLottoCount() = readIntOrNull()

    fun readInputBonusNumber() = readIntOrNull()

    private fun readIntOrNull(): Int? {
        val input = readln()
        return input.toIntOrNull()
    }

    fun readManualLottoNumbers() = readStringList()

    fun readInputWinningLotto() = readStringList()

    private fun readStringList(): List<String> {
        val input = readln()
        return input.split(",")
    }
}
