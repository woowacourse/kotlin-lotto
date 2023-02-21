package view

import domain.Lotto
import domain.Money

class InputView {
    fun inputAmount(): Money {
        return Money(readln().toInt())
    }

    fun inputNumberOfLottosToBuyManually(): Int {
        return readln().toInt()
    }

    fun inputLottosToBuyManually(count: Int): List<Lotto> =
        List(count) { Lotto.create(readln().split(",").map { it.trim().toInt() }) }

    fun inputWinningNumbers(): List<Int> {
        return readln().replace("\\s".toRegex(), "").split(",").map { it.toInt() }
    }

    fun inputBonusNumber(): Int {
        return readln().toInt()
    }
}
