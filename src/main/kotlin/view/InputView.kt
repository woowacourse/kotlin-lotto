package view

import domain.model.Money
import util.Constants.LOTTO_PRICE

object InputView {

    private const val INVALID_UNIT_EXCEPTION_MESSAGE = "구입 금액은 ${1000}원 단위여야 합니다."
    private const val MINIMUM_VALUE_EXCEPTION_MESSAGE = "구입 금액은 ${Money.ZERO}원 이상이어야 합니다."
    private const val INVALID_FORMAT_EXCEPTION_MESSAGE = "입력값은 정수여야 합니다."
    private const val WINNING_NUMBER_DELIMITER = ","

    fun readPurchaseAmount(): Long {
        return try {
            println("구입금액을 입력해 주세요.")
            val input = readln().toLong()
            require(input > 0L) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
            require(input % LOTTO_PRICE == 0L) { INVALID_UNIT_EXCEPTION_MESSAGE }
            input
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readWinningNumbers(): List<Int> {
        return try {
            println("\n지난 주 당첨 번호를 입력해 주세요.")
            readln().split(WINNING_NUMBER_DELIMITER).map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readBonusNumber(): Int {
        return try {
            println("보너스 볼을 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }
}
