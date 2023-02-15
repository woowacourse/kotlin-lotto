package lotto.view

import lotto.exception.Validator

object InputView {
    fun readInputMoney(): Int {
        val input = readln()
        Validator.checkInputMoney(input)
        return input.toInt()
    }
}