package view

class InputView {
    fun inputAmount(): Int? {
        return readln().toIntOrNull()
    }
    fun inputWinningNumbers(): IntArray? {
        val input = readln().replace("\\s".toRegex(), "").split(",").map { it.toIntOrNull() }
        if (input.contains(null)) { return null }
        return input.map { it!! }.toIntArray()
    }
    fun inputBonusNumber(): Int? {
        return readln().toIntOrNull()
    }
}
