package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber

class InputView {
    fun inputPurchase(): Int {
        println(INPUT_PURCHASE_MESSAGE)
        val input = readln().toIntOrNull()
        return when {
            input == null -> {
                println(ERROR_INVALID_PURCHASE_MESSAGE)
                inputPurchase()
            }
            input < 1000 || input % 1000 != 0 -> {
                println(ERROR_INVALID_PURCHASE_MESSAGE)
                inputPurchase()
            }
            else -> input
        }
    }

    fun inputManualCount(totalCount: Int): Int {
        println(INPUT_MANUAL_LOTTO_AMOUNT_MESSAGE)
        val input = readln().toIntOrNull()
        return when {
            input == null -> {
                println(ERROR_INVALID_MANUAL_COUNT_MESSAGE)
                inputManualCount(totalCount)
            }
            input !in 0..totalCount -> {
                println(ERROR_INVALID_MANUAL_COUNT_MESSAGE)
                inputManualCount(totalCount)
            }
            else -> input
        }
    }

    fun inputManualLottoNumber(manualCount: Int): List<List<Int>> {
        println(INPUT_MANUAL_LOTTO_NUMBER_MESSAGE)
        return List(manualCount) {
            val numbers = readln().split(",").mapNotNull { it.trim().toIntOrNull() }
            when {
                numbers.size != Lotto.LOTTO_NUMBERS_SIZE -> {
                    println(ERROR_INVALID_LOTTO_NUMBER_MESSAGE)
                    inputSingleLottoNumber()
                }
                numbers.any { it !in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE } -> {
                    println(ERROR_INVALID_LOTTO_NUMBER_MESSAGE)
                    inputSingleLottoNumber()
                }
                else -> numbers
            }
        }
    }

    private fun inputSingleLottoNumber(): List<Int> {
        val numbers = readln().split(",").mapNotNull { it.trim().toIntOrNull() }
        return when {
            numbers.size != Lotto.LOTTO_NUMBERS_SIZE -> {
                println(ERROR_INVALID_LOTTO_NUMBER_MESSAGE)
                inputSingleLottoNumber()
            }
            numbers.any { it !in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE } -> {
                println(ERROR_INVALID_LOTTO_NUMBER_MESSAGE)
                inputSingleLottoNumber()
            }
            else -> numbers
        }
    }

    fun inputWinningNumbers(): List<Int> {
        println(INPUT_WINNING_NUMBERS_MESSAGE)
        val numbers = readln().split(",").mapNotNull { it.trim().toIntOrNull() }
        return when {
            numbers.size != Lotto.LOTTO_NUMBERS_SIZE -> {
                println(ERROR_INVALID_WINNING_NUMBERS_MESSAGE)
                inputWinningNumbers()
            }
            numbers.any { it !in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE } -> {
                println(ERROR_INVALID_WINNING_NUMBERS_MESSAGE)
                inputWinningNumbers()
            }
            else -> numbers
        }
    }

    fun inputBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER_MESSAGE)
        val number = readln().toIntOrNull()
        return when {
            number == null -> {
                println(ERROR_INVALID_BONUS_NUMBER_MESSAGE)
                inputBonusNumber()
            }
            number !in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE -> {
                println(ERROR_INVALID_BONUS_NUMBER_MESSAGE)
                inputBonusNumber()
            }
            else -> number
        }
    }

    companion object {
        private const val INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_AMOUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_WINNING_NUMBERS_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."

        private const val ERROR_INVALID_PURCHASE_MESSAGE = "구입 금액은 1000원 단위여야하고 최소 1000원 이상이어야 합니다."
        private const val ERROR_INVALID_MANUAL_COUNT_MESSAGE = "수동 로또 개수는 총 개수를 초과할 수 없습니다."
        private const val ERROR_INVALID_LOTTO_NUMBER_MESSAGE = "로또 번호는 1~45 범위의 숫자 6개여야 합니다."
        private const val ERROR_INVALID_WINNING_NUMBERS_MESSAGE = "당첨 번호는 1~45 범위의 숫자 6개여야 합니다."
        private const val ERROR_INVALID_BONUS_NUMBER_MESSAGE = "보너스 번호는 1~45 범위의 숫자여야 합니다."
    }
}
