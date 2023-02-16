package lotto.domain

import java.util.regex.Pattern

object Validator {

    fun isNumber(value: String): Boolean = Pattern.matches("^[0-9]+$", value)
}
