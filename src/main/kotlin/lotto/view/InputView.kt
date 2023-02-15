package lotto.view

import lotto.domain.Lotto
import lotto.exception.Validator

object InputView {
    fun readInputMoney(): Int {
        val input = readln()
        Validator.checkInputMoney(input)
        return input.toInt()
    }

    fun readInputWinningLotto(): Lotto {
        val input = readln()
        return Lotto(input.split(",").map { it.toInt() })
    }

    fun readInputBonusNumber(): Int {
        val input = readln()
        Validator.checkInputBonusNumber(input)
        return input.toInt()
    }
}