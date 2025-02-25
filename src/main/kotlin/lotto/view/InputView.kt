package lotto.view

import lotto.validator.InputValidator

object InputView {
    fun inputPurchasePrice(): String {
        println("구입금액을 입력해 주세요.")
        return readln().also { InputValidator.ValidateNumber(it) }
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
