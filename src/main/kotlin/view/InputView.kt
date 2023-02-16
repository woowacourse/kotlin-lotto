package view

import domain.BonusNumber
import domain.Lotto
import domain.Money

class InputView {
    fun inputMoney(): Money {
        return getInputMoney(readLine())
    }

    private fun getInputMoney(input: String?): Money {
        return run {
            require(input != null) { INPUT_VALUE_ERROR_MESSAGE }
            require(input != "") { INPUT_VALUE_ERROR_MESSAGE }
            Money(input.toInt())
        }
    }

    fun inputWinningLotto(): Lotto {
        return getInputWinningLotto(readLine())
    }

    private fun getInputWinningLotto(input: String?): Lotto {
        return run {
            require(input != null) { INPUT_VALUE_ERROR_MESSAGE }
            require(input != "") { INPUT_VALUE_ERROR_MESSAGE }
            Lotto(input.split(",").map { number -> number.toInt() })
        }
    }

    fun inputBonusNumber(): BonusNumber {
        return getInputBonusNumber(readLine())
    }

    private fun getInputBonusNumber(input: String?): BonusNumber {
        return run {
            require(input != null) { INPUT_VALUE_ERROR_MESSAGE }
            require(input != "") { INPUT_VALUE_ERROR_MESSAGE }
            BonusNumber(input.toInt())
        }
    }

    companion object {
        const val INPUT_VALUE_ERROR_MESSAGE = "값이 입력되지 않았습니다."
    }
}
