package lotto.model

@JvmInline
value class Money(val amount: Int) {
    init {
        require(amount >= MIN_MONEY) { EXCEPTION_MONEY_RANGE }
    }

    operator fun div(other: Money): Int = amount / other.amount

    companion object {
        private const val MIN_MONEY = 0
        private const val EXCEPTION_MONEY_RANGE = "Money는 $MIN_MONEY 이상 이여야한다."
    }
}
