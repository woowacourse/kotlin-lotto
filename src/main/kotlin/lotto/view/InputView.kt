package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber

class InputView {
    fun inputPurchase(): Int? {
        println(INPUT_PURCHASE_MESSAGE)
        return readln().toIntOrNull()?.takeIf { it >= 1000 && it % 1000 == 0 }
    }

    fun inputManualCount(totalCount: Int): Int? {
        println(INPUT_MANUAL_LOTTO_AMOUNT_MESSAGE)
        return readln().toIntOrNull()?.takeIf { it in 0..totalCount }
    }

    fun inputManualLottoNumber(): List<Int>? {
        println(INPUT_MANUAL_LOTTO_NUMBER_MESSAGE)
        val numbers = readln().split(",").mapNotNull { it.trim().toIntOrNull() }

        return numbers.takeIf { it.size == Lotto.LOTTO_NUMBERS_SIZE }
    }

    fun inputWinningNumbers(): List<Int>? {
        println(INPUT_WINNING_NUMBERS_MESSAGE)
        val numbers = readln().split(",").mapNotNull { it.trim().toIntOrNull() }

        return numbers.takeIf { it.size == Lotto.LOTTO_NUMBERS_SIZE }
    }

    fun inputBonusNumber(): Int? {
        println(INPUT_BONUS_NUMBER_MESSAGE)
        return readln().toIntOrNull()?.takeIf { it in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE }
    }

    companion object {
        private const val INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_NUMBERS_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_AMOUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    }
}
