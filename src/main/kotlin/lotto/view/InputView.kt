package lotto.view

import lotto.validator.InputValidator

object InputView {
    fun inputPurchasePrice(): String {
        println("구입금액을 입력해 주세요.")
        return readln().also { InputValidator.ValidateNumber(it) }
    }

    fun inputManualLottoAmount(): String {
        println("수동으로 구매할 로또 수를 입력해 주세요. ")
        return readln()
    }

    fun inputManualLottoNumber(): String {
        println("수동으로 구매할 번호를 입력해주세요. ")
        return readln()
    }

    fun inputWinningNumber(): List<String> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        val numbers =
            readln().split(",").map {
                it.trim().also { InputValidator.ValidateNumber(it) }
            }
        return numbers
    }

    fun inputBonusNumber(): String {
        println("보너스 볼을 입력해 주세요.")
        return readln().also { InputValidator.ValidateNumber(it) }
    }
}
