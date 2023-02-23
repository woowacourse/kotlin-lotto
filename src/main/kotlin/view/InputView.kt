package view

import domain.Count
import domain.Lotto

class InputView {
    fun inputAmount(): Int? =
        readln().toIntOrNull()

    fun inputNumberOfLottosToBuyManually(): Int? =
        readln().toIntOrNull()

    fun inputLottosToBuyManually(count: Count): List<Lotto> =
        List(count.value) { Lotto.create(readln().split(",").map { it.trim().toInt() }) }

    fun inputWinningNumbers(): List<Int> {
        return readln().replace("\\s".toRegex(), "").split(",").map { it.toInt() }
    }

    fun inputBonusNumber(): Int {
        return readln().toInt()
    }

    companion object {

    }
}
