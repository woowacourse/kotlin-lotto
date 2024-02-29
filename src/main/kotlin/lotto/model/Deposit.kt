package lotto.model

import lotto.exception.ErrorCode.INSUFFICIENT_DEPOSIT
import lotto.exception.ErrorCode.INVALID_PURCHASE_AMOUNT
import lotto.exception.ExceptionsHandler.handleValidation

data class Deposit(val money: Money = DEFAULT_MONEY) {
    operator fun plus(other: Int): Deposit {
        handleValidation(INVALID_PURCHASE_AMOUNT) { money.value + other >= MIN_PRICE }
        return copy(money = Money(money.value + other))
    }

    operator fun minus(other: Int): Deposit {
        handleValidation(INSUFFICIENT_DEPOSIT) { money.value >= other }
        return copy(money = Money(money.value - other))
    }

    companion object {
        private val DEFAULT_MONEY = Money(0)
        private const val MIN_PRICE = 1_000
    }
}
