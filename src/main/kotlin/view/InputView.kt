package view

object InputView {
    private const val SEPARATOR = ", "

    fun inputMoney(): String {
        return readln()
    }

    fun inputManualCount(): String {
        return readln()
    }

    fun inputManualLotto(): List<String> {
        return (readln().split(SEPARATOR))
    }

    fun inputWinningNumbers(): List<String> {
        return (readln().split(SEPARATOR))
    }

    fun inputBonusNumber(): String {
        return readln()
    }
}
