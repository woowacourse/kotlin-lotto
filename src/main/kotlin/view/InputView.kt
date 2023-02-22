package view

import domain.Lotto

class InputView {
    fun inputAmount(): Int? =
        readln().toIntOrNull()

    fun inputNumberOfLottosToBuyManually(): Int? =
        readln().toIntOrNull()

    fun inputLottosToBuyManually(count: Int): List<Lotto> =
        List(count) { Lotto.create(readln().split(",").map { it.trim().toInt() }) }

    fun inputWinningNumbers(): List<Int> {
        return readln().replace("\\s".toRegex(), "").split(",").map { it.toInt() }
    }

    fun inputBonusNumber(): Int {
        return readln().toInt()
    }

    companion object {

    }
}
