package util

import domain.model.Lotto

class ConvertMachine {
    companion object {
        fun toNumberList(lotto: Lotto): List<Int> {
            return lotto.numbers.map { it.number }
        }
    }
}
