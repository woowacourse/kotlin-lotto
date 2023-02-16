package view

class InputView {
    fun inputAmount(): Int {
        return readln().toInt()
    }
    fun inputWinningNumbers(): IntArray {
        return readln().replace("\\s".toRegex(), "").split(",").map { it.toInt() }.toIntArray()
    }
    fun inputBonusNumber(): Int {
        return readln().toInt()
    }
}
