package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.util.Constant.LOTTO_LEN
import lotto.util.Constant.LOTTO_PRICE
import lotto.util.Constant.SEPARATOR

const val WRONG_INPUT = "잘못 된 입력 값입니다."
const val NEED_NUMBER = "로또 넘버는 숫자를 입력해야 합니다."

object Verifier {
    fun inputCharge(userInput: String?): Int? {
        return try {
            userInput?.toIntOrNull() ?: throw (IllegalArgumentException(WRONG_INPUT))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    fun inputWinning(userInput: String?): Lotto? {
        return try {
            val inputNumbers =
                userInput
                    ?.split(SEPARATOR)
                    ?.map { it.trim().toIntOrNull() ?: throw IllegalArgumentException(NEED_NUMBER) }
                    ?.map { LottoNumber.of(it) }?.toSet()

            if (inputNumbers?.size != LOTTO_LEN) {
                throw IllegalArgumentException("정확히 ${LOTTO_LEN}개의 숫자를 입력해야 합니다.")
            }
            Lotto(inputNumbers)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    fun inputBonusNumber(userInput: String?): Int? {
        return try {
            userInput?.toIntOrNull() ?: throw (IllegalArgumentException(WRONG_INPUT))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    fun inputLottoNumber(userInput: String?): Lotto? {
        return try {
            val inputNumbers =
                userInput
                    ?.split(SEPARATOR)
                    ?.map { it.trim().toIntOrNull() ?: throw IllegalArgumentException(NEED_NUMBER) }
                    ?.map { LottoNumber.of(it) }?.toSet()

            if (inputNumbers?.size != LOTTO_LEN) {
                throw IllegalArgumentException("정확히 ${LOTTO_LEN}개의 숫자를 입력해야 합니다.")
            }
            Lotto(inputNumbers)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    fun inputHowManyManually(
        userInput: String?,
        charge: Int,
    ): Int? {
        return try {
            val inputManuallyCount = userInput?.toIntOrNull() ?: throw (IllegalArgumentException(WRONG_INPUT))

            if (inputManuallyCount * LOTTO_PRICE > charge) {
                throw IllegalArgumentException("수동으로 구매할 로또 수는 구입금액을 넘을 수 없습니다.")
            }
            inputManuallyCount
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }
}
