package lotto.model

sealed class ValidationResult {
    data object Success : ValidationResult()

    sealed class Error : ValidationResult() {
        data object DuplicateError : Error()

        data object NumberSizeError : Error()

        data object NumberRangeError : Error()

        data object OverMoneyError : Error()
    }
}
