package lotto.validator

class InputValidator {
    fun validatePurchaseAmount(input: String) {
        val purchaseAmount = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_INTEGER)
        if (purchaseAmount % 1000 != 0) throw IllegalArgumentException(ERROR_NOT_THOUSAND_UNIT)
        if (purchaseAmount <= 0) throw IllegalArgumentException(ERROR_UNDER_ZERO)
    }

    companion object {
        const val ERROR_NOT_INTEGER = "[ERROR] 양수만 입력 가능합니다."
        const val ERROR_NOT_THOUSAND_UNIT = "[ERROR] 구매 가격이 1000 단위가 아닙니다."
        const val ERROR_UNDER_ZERO = "[ERROR] 구매 가격은 양수여야 합니다."
    }
}
