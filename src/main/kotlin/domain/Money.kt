package domain

import util.PREFIX

@JvmInline
value class Money(val amount: Long) {
    constructor(amount: Int) : this(amount.toLong())

    init {
        require(amount >= 0) { "$PREFIX 돈이 음수일 수 없습니다. 들어온 돈은 ${amount}입니다" }
    }

    operator fun plus(otherMoney: Money): Money {
        return Money(amount + otherMoney.amount)
    }

    operator fun minus(otherMoney: Money): Money {
        return Money(amount - otherMoney.amount)
    }

    operator fun div(otherMoney: Money): Double {
        return amount.toDouble() / otherMoney.amount
    }

    operator fun times(time: Int): Money {
        return Money(amount * time)
    }
}
