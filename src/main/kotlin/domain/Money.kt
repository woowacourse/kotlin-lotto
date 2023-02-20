package domain

import util.PREFIX

@JvmInline
value class Money(val amount: Long) {
    constructor(amount: Int) : this(amount.toLong())

    init {
        require((amount % 1000L == 0L) and (amount != 0L)) { "$PREFIX 받은 돈이 1000원 단위여야합니다." }
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
