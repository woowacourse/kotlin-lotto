package lotto.view

import lotto.validator.InputValidator

object InputView {
    fun inputPurchasePrice(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        return InputValidator.validateNumber(input) ?: inputPurchasePrice()
    }

    fun inputManualLottoAmount(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요. ")
        val input = readln()
        return InputValidator.validateNumber(input) ?: inputPurchasePrice()
    }

    fun inputLottoNumber(): List<Int> {
        val input = readln()
        val numbers =
            input.split(',')
                .map { it.trim() }
                .mapNotNull { number ->
                    InputValidator.validateNumber(number)?.toInt()
                }

        return if (numbers.size == input.split(',').size) {
            numbers
        } else {
            println("잘못된 입력입니다. 유효한 숫자만 입력해주세요.")
            inputLottoNumber()
        }
    }

    fun inputWinningNumber(): List<Int> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return inputLottoNumber()
    }

    fun inputBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        val input = readln()
        return InputValidator.validateNumber(input) ?: inputPurchasePrice()
    }
}
