package view

import domain.Lotto
import domain.Money

class InputView {
    fun inputMoney(): Money {
        return getInputMoney(readLine())
    }

    private fun getInputMoney(input: String?): Money {
        return run {
            require(input != null) {}
            require(input != "") {}
            Money(input.toInt())
        }
    }

    fun inputWinningLotto(): Lotto? {
        return getInputWinningLotto(readLine())
    }

    private fun getInputWinningLotto(input: String?): Lotto {
        return run {
            require(input != null) {}
            require(input != "") {}
            Lotto(input.split(",").map { number -> number.toInt() })
        }
    }
}
