package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.util.Constant.LOTTO_LEN

const val SEPARATOR = ","
const val WRONG_INPUT = "잘못 된 입력 값입니다."
const val NEED_NUMBER = "로또 넘버는 숫자를 입력해야 합니다."

object Verifier {
    fun inputCharge(userInput: String?): Int? {
        return try {
            userInput
                ?.toIntOrNull()
                ?: throw (IllegalArgumentException(WRONG_INPUT))
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
                    ?.map { LottoNumber.of(it) }
                    ?.toSet()

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
            userInput
                ?.toIntOrNull()
                ?: throw (IllegalArgumentException(WRONG_INPUT))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }
}
