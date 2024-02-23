package lotto.controller

import lotto.model.Lotto

private const val SEPARATOR = ","
private const val WRONG_INPUT = "잘못 된 입력 값입니다."
private const val NEED_NUMBER = "로또 넘버는 숫자를 입력해야 합니다."

object Verifier {
    fun inputCharge(): Int {
        return try {
            readlnOrNull()
                ?.toIntOrNull()
                ?: throw (IllegalArgumentException(WRONG_INPUT))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputCharge()
        }
    }

    fun inputWinning(): Lotto {
        return try {
            Lotto(
                readlnOrNull()
                    ?.split(SEPARATOR)
                    ?.map {
                        it.trim().toIntOrNull() ?: throw (IllegalArgumentException(NEED_NUMBER))
                    }?.toSet()
                    ?: throw (IllegalArgumentException(WRONG_INPUT))
            )
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputWinning()
        }
    }

    fun inputBonusNumber(): Int {
        return try {
            readlnOrNull()?.toIntOrNull()
                ?: throw (IllegalArgumentException(WRONG_INPUT))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputBonusNumber()
        }
    }
}
