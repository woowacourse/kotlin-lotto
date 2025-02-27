package lotto.view

import lotto.validator.InputValidator

object InputView {
    fun inputPurchasePrice(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln().also { InputValidator.ValidateNumber(it) }
        return input.toInt()
    }

    fun inputManualLottoAmount(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요. ")
        val input = readln().also { InputValidator.ValidateNumber(it) }
        return input.toInt()
    }

    fun inputManualLottoNumber(): List<Int> {
        val numbers =
            readln().split(',').map {
                it.trim().also { InputValidator.ValidateNumber(it) }
                it.toInt()
            }
        return numbers
    }

    fun inputWinningNumber(): List<Int> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        val numbers =
            readln().split(",").map {
                it.trim().also { InputValidator.ValidateNumber(it) }
                it.toInt()
            }
        return numbers
    }

    fun inputBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        val input = readln().also { InputValidator.ValidateNumber(it) }
        return input.toInt()
    }
}
