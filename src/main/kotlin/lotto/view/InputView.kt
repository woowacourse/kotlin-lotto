package lotto.view

class InputView {
    fun readPurchaseAmount(): Int = readln().toInt()

    fun readWinningNumbers(): List<Int> {
        val rawWinningNumbers = readln().trim().split(",")
        return rawWinningNumbers.map { number -> number.toInt() }
    }

    fun readBonusNumber(): Int = readln().toInt()
}
