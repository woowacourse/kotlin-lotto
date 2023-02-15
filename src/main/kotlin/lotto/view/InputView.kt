package lotto.view

import lotto.exception.Validator

class InputView(private val validator: Validator) {
    fun readInputMoney(): Int {
        val input = readln()
        validator.checkInputMoney(input)
        return input.toInt()
    }
}