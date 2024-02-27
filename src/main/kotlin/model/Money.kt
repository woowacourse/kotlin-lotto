package model

import model.profit.ProfitRate
import java.math.BigDecimal
import java.math.RoundingMode

class Money private constructor(val amount: BigDecimal) : Comparable<Money> {
    operator fun plus(other: Money): Money = Money(this.amount.add(other.amount))

    operator fun minus(other: Money): Money = Money(this.amount.minus(other.amount))

    operator fun times(quantity: Quantity): Money = Money(this.amount.times(quantity.count.toBigDecimal()))

    operator fun div(other: Money): Double = this.amount.divide(other.amount, 2, RoundingMode.HALF_UP).toDouble()

    operator fun rem(other: Money): Money = Money(this.amount.rem(other.amount))

    override fun compareTo(other: Money): Int = (amount - other.amount).toInt()

    fun calculateProfitRate(totalWinningPrize: Money): ProfitRate = ProfitRate(totalWinningPrize / this)

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

        fun wons(amount: Int): Money = Money(BigDecimal(amount))

        fun wons(amount: Long): Money = Money(BigDecimal.valueOf(amount))

        fun from(input: String): Money {
            val amount = input.toLongOrNull()
            if (amount != null && amount >= 0) return wons(amount)
            val bigAmount = BigDecimal(input)
            require(bigAmount >= BigDecimal.ZERO) { ERROR_INVALID_PURCHASE_AMOUNT }

            return Money(bigAmount)
        }

        private const val ERROR_INVALID_PURCHASE_AMOUNT = "제대로 된 금액을 지불해야 합니다."
    }
}
