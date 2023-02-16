package lotto.domain

import java.util.regex.Pattern

object Validator {
    const val ERROR_NOT_NUMBER = "숫자를 입력해야 합니다."
    const val ERROR_INPUT_HANDLER = "입력값 검증과정에 문제가 있습니다."
    fun isNumber(value: String): Boolean = Pattern.matches("^[0-9]+$", value)
}
