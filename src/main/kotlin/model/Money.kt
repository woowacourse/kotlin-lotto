package model

class Money private constructor(val amount: Long) : Comparable<Money> {
    operator fun plus(other: Money): Money = Money(this.amount + other.amount)

    operator fun minus(other: Money): Money = Money(this.amount - other.amount)

    operator fun times(quantity: Quantity): Money = Money(this.amount * quantity.count)

    operator fun div(other: Money): Double = this.amount / other.amount.toDouble()

    operator fun rem(other: Money): Money = Money(this.amount % other.amount)

    override fun compareTo(other: Money): Int = (amount - other.amount).toInt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (amount != other.amount) return false

        return true
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }

    override fun toString(): String {
        return "Money(amount=$amount)"
    }

    companion object {
        val ZERO: Money = wons(0)

        fun wons(amount: Int): Money = Money(amount.toLong())

        fun wons(amount: Long): Money = Money(amount)

        fun from(number: Long): Money {
            require(number >= 0) { "${number}를 지불하셨습니다. 지불 금액은 0 이상이어야 합니다." }
            return wons(number)
        }
    }
}
