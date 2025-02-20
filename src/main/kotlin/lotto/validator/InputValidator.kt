package lotto.validator

class InputValidator {
    fun validatePurchaseAmount(input: String) {
        val purchaseAmount = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_INTEGER)
        if (purchaseAmount % LOTTO_TICKET_PRICE != REMAINING) throw IllegalArgumentException(ERROR_NOT_THOUSAND_UNIT)
        if (purchaseAmount <= SMALL_CHANGE) throw IllegalArgumentException(ERROR_UNDER_ZERO)
    }

    fun validateWinningNumber(input: List<String>) {
        if (input.size != MAX_LOTTO_SIZE) throw IllegalArgumentException(ERROR_NOT_SIX)
        if (input.toSet().size != input.size) throw IllegalArgumentException(ERROR_NOT_DUPLICATE_NUMBER)
        if (input.map { it.toIntOrNull() }.contains(null)) throw IllegalArgumentException(ERROR_NOT_INTEGER)
        val winningNumber = input.map { it.toInt() }
        if (!winningNumber.all { checkInRange(it) }) throw IllegalArgumentException(ERROR_OUT_OF_RANGE)
    }

    fun validateBonusNumber(
        input: String,
        winningNumber: List<Int>,
    ) {
        val bonusNumber = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_INTEGER)
        if (winningNumber.contains(bonusNumber)) throw IllegalArgumentException(ERROR_NOT_DUPLICATE_NUMBER)
        if (!checkInRange(bonusNumber)) throw IllegalArgumentException(ERROR_OUT_OF_RANGE)
    }

    private fun checkInRange(number: Int): Boolean {
        return number in MIN_BOUND..MAX_BOUND
    }

    companion object {
        const val ERROR_NOT_INTEGER = "[ERROR] 양수만 입력 가능합니다."
        const val ERROR_NOT_THOUSAND_UNIT = "[ERROR] 구매 가격이 1000 단위가 아닙니다."
        const val ERROR_UNDER_ZERO = "[ERROR] 구매 가격은 양수여야 합니다."
        const val ERROR_NOT_DUPLICATE_NUMBER = "[ERROR] 로또 당첨 번호는 중복될 수 없습니다."
        const val ERROR_OUT_OF_RANGE = "[ERROR] 로또 당첨 번호는 1~45 사이어야 합니다."
        const val ERROR_NOT_SIX = "[ERROR] 로또 당첨 번호는 6개여야 합니다."

        const val MAX_LOTTO_SIZE = 6
        const val MIN_BOUND = 1
        const val MAX_BOUND = 45
        const val LOTTO_TICKET_PRICE = 1_000
        const val REMAINING = 0
        const val SMALL_CHANGE = 0
    }
}
