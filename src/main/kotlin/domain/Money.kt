package domain

import util.PREFIX

@JvmInline
value class Money(private val amount: Double) {
    constructor(amount: Int) : this(amount.toLong())
    constructor(amount: Long) : this(amount.toDouble())

    init {
        require(amount >= 0) { "$PREFIX 돈이 음수일 수 없습니다. 들어온 돈은 ${amount}입니다" }
    }

    fun toInt(): Int = amount.toInt()
    fun toLong(): Long = amount.toLong()
    fun toDouble(): Double = amount.toDouble()

    operator fun plus(otherMoney: Money): Money {
        return Money(amount + otherMoney.amount)
    }

    operator fun minus(otherMoney: Money): Money {
        return Money(amount - otherMoney.amount)
    }

    operator fun div(otherMoney: Money): Money {
        return Money(amount / otherMoney.amount)
    }

    operator fun times(time: Int): Money {
        return Money(amount * time)
    }
}
