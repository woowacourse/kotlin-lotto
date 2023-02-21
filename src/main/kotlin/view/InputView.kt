package view

class InputView {
    fun inputAmount(): Int {
        return readln().toInt()
    }

    fun inputNumberOfLottosToBuyManually(): Int {
        return readln().toInt()
    }

    fun inputWinningNumbers(): List<Int> {
        return readln().replace("\\s".toRegex(), "").split(",").map { it.toInt() }
    }

    fun inputBonusNumber(): Int {
        return readln().toInt()
    }
}
