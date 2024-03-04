package lottogame.model

@JvmInline
value class Money(val amount: Int) {
    init {
        require(amount >= MIN_MONEY) { EXCEPTION_MONEY_RANGE }
    }

    operator fun div(other: Money): Int {
        require(other.amount != 0) { EXCEPTION_ZERO_DIVIDE }
        return amount / other.amount
    }

    operator fun plus(other: Money): Money = Money(amount + other.amount)

    operator fun minus(other: Money): Money = Money(amount - other.amount)

    operator fun times(number: Int): Money = Money(amount * number)

    operator fun compareTo(other: Money) = amount - other.amount

    companion object {
        private const val MIN_MONEY = 0
        private const val EXCEPTION_MONEY_RANGE = "Money는 $MIN_MONEY 이상 이여야한다."
        private const val EXCEPTION_ZERO_DIVIDE = "0으로 나눌 수 없다"
    }
}
