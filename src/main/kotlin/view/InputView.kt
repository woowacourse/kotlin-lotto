package view

import domain.Lotto

class InputView {
    fun inputAmount(): Int =
        readln().toIntOrNull().let {
            var amount = it
            while (amount == null) {
                println("int 타입으로 입력 바랍니다.")
                amount = readln().toIntOrNull()
            }
            amount
        }

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
