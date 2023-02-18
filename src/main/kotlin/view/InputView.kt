package view

import domain.BonusNumber
import domain.Lotto
import domain.LottoNumber
import domain.Money

class InputView {
    fun inputMoney(): Money {
        return getInputMoney(readLine())
    }

    private fun getInputMoney(input: String?): Money {
        require(input != null) { INPUT_VALUE_ERROR_MESSAGE }
        require(input != "") { INPUT_VALUE_ERROR_MESSAGE }
        return Money(input.toInt())
    }

    fun inputWinningLotto(): Lotto {
        return getInputWinningLotto(readLine())
    }

    private fun getInputWinningLotto(input: String?): Lotto {
        require(input != null) { INPUT_VALUE_ERROR_MESSAGE }
        require(input != "") { INPUT_VALUE_ERROR_MESSAGE }
        var winningNumber = input.split(",").map { number -> LottoNumber.from(number.toInt()) }
        return Lotto(winningNumber)
    }

    fun inputBonusNumber(): BonusNumber {
        return getInputBonusNumber(readLine())
    }

    private fun getInputBonusNumber(input: String?): BonusNumber {
        require(input != null) { INPUT_VALUE_ERROR_MESSAGE }
        require(input != "") { INPUT_VALUE_ERROR_MESSAGE }
        return BonusNumber(input.toInt())
    }

    companion object {
        const val INPUT_VALUE_ERROR_MESSAGE = "값이 입력되지 않았습니다."
    }
}
