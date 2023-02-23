package view

import domain.LottoNumber
import domain.Money

class InputView {
    fun inputMoney(): Money {
        return Money(getInputMoney())
    }

    private fun getInputMoney(): Int {
        while (true) {
            println(INPUT_MONEY_MESSAGE)
            return readln().toIntOrNull() ?: continue
        }
    }

    fun InputManualLottoCount(): Int {
        while (true) {
            println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE)
            return readln().toIntOrNull() ?: continue
        }
    }

    fun inputManualLottoNumber(count: Int): List<List<Int>> {
        return List(count) { getManualLottoNumber() }
    }

    private fun getManualLottoNumber(): List<Int> {
        while (true) {
            println(INPUT_MANUAL_LOTTO_NUMBER_MESSAGE)
            val lotto = readln().split(",").map { it.toIntOrNull() }
            if (lotto.contains(null)) { continue }
            return lotto.filterNotNull()
        }
    }

    fun inputWinningLotto(): List<Int> {
        while (true) {
            println(INPUT_WINNING_LOTTO_MESSAGE)
            val winningNumber = readln().split(",").map { it.toIntOrNull() }
            if (winningNumber.contains(null)) { continue }
            return winningNumber.filterNotNull()
        }
    }

    fun inputBonusNumber(): LottoNumber {
        while (true) {
            println(INPUT_BONUS_NUMBER_MESSAGE)
            val number = readln().toIntOrNull() ?: continue
            return LottoNumber.from(number)
        }
    }

    companion object {
        const val INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
        const val INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        const val INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        const val INPUT_MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    }
}
