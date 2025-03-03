package lotto.validator

object InputValidator {
    fun validateNumber(value: String): Int? {
        return if (value.isNotBlank() && value.all { it.isDigit() }) {
            value.toInt()
        } else {
            null
        }
    }
}
