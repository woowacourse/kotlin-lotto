package lotto.model

data class Money(private val money: Long) {
    init {
        require(money >= 0)
    }

    operator fun plus(other: Money) = Money(money + other.money)

    operator fun minus(other: Money) = Money(money - other.money)

    operator fun times(other: Int) = Money(money * other)

    operator fun div(other: Money): Float? {
        if (other.money == 0L) {
            return null
        }
        return money.toFloat() / other.money
    }

    operator fun rem(other: Money) = Money(money % other.money)

    override fun toString(): String = money.toString()

    operator fun compareTo(other: Money): Int = (money - other.money).toInt()
}

fun Iterable<Money>.sum(): Money {
    var sum = Money(0)
    for (iterable in this) {
        sum += iterable
    }
    return sum
}
