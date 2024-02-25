package lotto.controller

import lotto.model.LottoNumber

private const val SEPARATOR = ","
private const val WRONG_INPUT = "잘못 된 입력 값입니다."
private const val NEED_NUMBER = "로또 넘버는 숫자를 입력해야 합니다."

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

fun inputWinning(userInput: String?): LottoNumber? {
    return try {
        LottoNumber(
            userInput
                ?.split(SEPARATOR)
                ?.map {
                    it.trim().toIntOrNull() ?: throw (IllegalArgumentException(NEED_NUMBER))
                }?.toSet()
                ?: throw (IllegalArgumentException(WRONG_INPUT))
        )
    } catch (e: IllegalArgumentException) {
        println(e.message)
        null
    }
}

fun inputBonus(userInput: String?): Int? {
    return try {
        userInput
            ?.toIntOrNull()
            ?: throw (IllegalArgumentException(WRONG_INPUT))
    } catch (e: IllegalArgumentException) {
        println(e.message)
        null
    }
}
