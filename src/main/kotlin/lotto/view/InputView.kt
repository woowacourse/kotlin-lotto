package lotto.view

class InputView {
    fun readPurchaseAmount(): Int = readln().toInt()

    fun readWinningNumbers(): List<Int> {
        val rawWinningNumbers = readln().split(",").map { number -> number.trim() }
        return rawWinningNumbers.map { number -> number.toInt() }
    }

    fun readBonusNumber(): Int = readln().toInt()
}
