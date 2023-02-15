package view

class InputView {
    fun inputAmount(): Int {
        return readln().toInt()
    }
    fun inputWinningNumbers(): Set<Int> {
        return readln().replace("\\s".toRegex(), "").split(",").map { it.toInt() }.toSet()
    }
    fun inputBonusNumber(): Int {
        return readln().toInt()
    }
}
