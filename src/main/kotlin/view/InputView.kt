package view

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
}
