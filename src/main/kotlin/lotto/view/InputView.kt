package lotto.view

class InputView {
    fun readPurchaseAmount(): String = readln()

    fun readWinningNumbers(): String {
        val rawWinningNumbers = readln()
        return rawWinningNumbers
    }

    fun readBonusNumber(): String = readln()
}
