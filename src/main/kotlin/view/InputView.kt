package view

import domain.Lotto
import domain.LottoNumber
import domain.Money

class InputView {
    fun inputMoney(): Money {
        return Money(getInputMoney().toInt())
    }

    private fun getInputMoney(): String {
        while (true) {
            println(INPUT_MONEY_MESSAGE)
            return readlnOrNull() ?: continue
        }
    }

    fun inputWinningLotto(): Lotto {
        println(INPUT_WINNING_LOTTO_MESSAGE)
        return getInputWinningLotto(readLine())
    }

    private fun getInputWinningLotto(input: String?): Lotto {
        require(!input.isNullOrEmpty()) { INPUT_VALUE_ERROR_MESSAGE }
        val winningNumber = input.split(",").map { number -> LottoNumber.from(number.toInt()) }
        return Lotto(winningNumber)
    }

    fun inputBonusNumber(): LottoNumber {
        println(INPUT_BONUS_NUMBER_MESSAGE)
        return getInputBonusNumber(readLine())
    }

    private fun getInputBonusNumber(input: String?): LottoNumber {
        require(!input.isNullOrEmpty()) { INPUT_VALUE_ERROR_MESSAGE }
        return LottoNumber.from(input.toInt())
    }

    companion object {
        const val INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
        const val INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."

        const val INPUT_VALUE_ERROR_MESSAGE = "값이 입력되지 않았습니다."
    }
}
