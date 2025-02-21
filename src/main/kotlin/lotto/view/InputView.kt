package lotto.view

class InputView {
    fun readPurchaseAmount(): Int = readln().toInt()

    fun readWinningNumbers(): String {
        val rawWinningNumbers = readln()
        return rawWinningNumbers
    }

    fun readBonusNumber(): String = readln()
}
