package lotto.validator

class InputValidator {
    fun validateNumber(value: String) {
        runCatching {
            require(value.isNotBlank()) { "입력 값은 공백이 될 수 없습니다" }
            require(value.all { it.isDigit() }) { "입력 값은 숫자만 가능합니다" }
        }
    }
}
