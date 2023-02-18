package domain.money

import util.common.constant.ERROR_PREFIX

class Money(val amount: Int) {
    init {
        require(amount >= MIN_AMOUNT) { ERROR_MESSAGE_NEGATIVE_AMOUNT }
    }

    fun divideByThousand() = amount.div(THOUSAND)

    companion object {
        private const val MIN_AMOUNT = 0
        private const val THOUSAND = 1000

        private const val ERROR_MESSAGE_NEGATIVE_AMOUNT = "$ERROR_PREFIX 금액은 0보다 크거나 같은 값이어야 합니다."
        private const val ERROR_MESSAGE_AMOUNT_NOT_NUMERIC = "$ERROR_PREFIX 금액은 숫자여야 합니다."

        fun from(amount: String): Money =
            Money(requireNotNull(amount.toIntOrNull()) { ERROR_MESSAGE_AMOUNT_NOT_NUMERIC })
    }
}
