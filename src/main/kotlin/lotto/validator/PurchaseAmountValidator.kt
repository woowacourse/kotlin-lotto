package lotto.validator

import lotto.constant.ErrorConstants

class PurchaseAmountValidator(input: String) {
    init {
        require(input.toIntOrNull() != null) { ErrorConstants.ERROR_NOT_INTEGER }
        require(input.toInt() % LOTTO_TICKET_PRICE == REMAINING) { ErrorConstants.ERROR_NOT_THOUSAND_UNIT }
        require(input.toInt() > SMALL_CHANGE) { ErrorConstants.ERROR_NOT_NEGATIVE_NUMBER }
    }

    companion object {
        const val REMAINING = 0
        const val SMALL_CHANGE = 0
        const val LOTTO_TICKET_PRICE = 1_000
    }
}
