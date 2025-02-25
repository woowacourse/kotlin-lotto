package lotto.view

import lotto.InputValidator

class LottoInputView {
    fun inputPurchase(): Double {
        println(INPUT_PURCHASE_MESSAGE)
        val purchase = readln().also { InputValidator(it) }
        return purchase.toDouble()
    }

    fun inputManualPurchase(): Int {
        println(INPUT_MANUAL_PURCHASE_MESSAGE)
        val manualPurchase = readln().also { InputValidator(it) }
        return manualPurchase.toInt()
    }

    fun inputWinningNumbers(): List<Int> {
        println("\n" + INPUT_WINNING_NUMBERS_MESSAGE)
        val input = readln()

        return input
            .split(",")
            .map { it.trim().also { InputValidator(it) }.toInt() }
            .toList()
    }

    fun inputBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER_MESSAGE)
        val number = readln().also { InputValidator(it) }
        return number.toInt()
    }

    companion object {
        private const val INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요."
        private const val INPUT_MANUAL_PURCHASE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
