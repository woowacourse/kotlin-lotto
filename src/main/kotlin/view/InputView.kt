package view

object InputView {
    private const val SEPARATOR = ", "

    fun inputMoney(): String {
        return readln()
    }

    fun inputWinningNumbers(): List<String> {
        return (readln().split(SEPARATOR))
    }

    fun inputBonusNumber(): String {
        return readln()
    }
}
