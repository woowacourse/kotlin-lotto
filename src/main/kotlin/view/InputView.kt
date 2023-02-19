package view

import domain.Lotto
import domain.LottoNumber
import domain.Money

class InputView {
    fun askPurchaseMoney(): Money {
        while (true) {
            println("구입금액을 입력해 주세요.")
            return Money.from(inputMoney() ?: continue)
        }
    }

    private fun inputMoney(): Int? {
        return readln().toIntOrNull()
    }

    fun askWinningNumbers(): Lotto {
        while (true) {
            println("지난 주 당첨 번호를 입력해 주세요.")
            val numbers = inputWinningNumbers() ?: continue
            return Lotto(*numbers)
        }
    }

    private fun inputWinningNumbers(): IntArray? {
        val input = readln().replace("\\s".toRegex(), "").split(",").map { it.toIntOrNull() }
        if (input.contains(null)) { return null }
        return input.map { it!! }.toIntArray()
    }

    fun askBonusNumber(): LottoNumber {
        while (true) {
            println("보너스 볼을 입력해 주세요.")
            return LottoNumber.from(inputBonusNumber() ?: continue)
        }
    }

    private fun inputBonusNumber(): Int? {
        return readln().toIntOrNull()
    }
}
