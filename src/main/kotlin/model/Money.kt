package model

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

data class Money(val amount: BigDecimal) : Comparable<Money> {
    operator fun plus(other: Money): Money = Money(this.amount.add(other.amount))

    operator fun minus(other: Money): Money = Money(this.amount.minus(other.amount))

    operator fun times(count: Int): Money = Money(this.amount.times(count.toBigDecimal()))

    operator fun div(other: Money): Double = this.amount.divide(other.amount, 2, RoundingMode.HALF_UP).toDouble()

    operator fun rem(other: Money): Money = Money(this.amount.rem(other.amount))

    override fun compareTo(other: Money): Int = (amount - other.amount).toInt()

    companion object {
        fun from(amount: Int): Money {
            require(amount <= MAX_PURCHASE_AMOUNT) { ERROR_EXCEED_MAX_PURCHASE_AMOUNT }
            require(amount >= PRICE) { ERROR_LESS_THAN_MIN_PURCHASE_AMOUNT }
            val purchasableAmount = amount - (amount % 1000)

            return Money(BigDecimal(purchasableAmount))
        }

        private const val MIN_PRICE_AMOUNT = 1_000
        private const val MAX_PRICE_AMOUNT = 100_000

        private val decimalFormat = DecimalFormat("#,###")

        private const val PRICE = MIN_PRICE_AMOUNT
        private const val MAX_PURCHASE_AMOUNT = MAX_PRICE_AMOUNT

        private val ERROR_EXCEED_MAX_PURCHASE_AMOUNT = "${decimalFormat.format(MAX_PRICE_AMOUNT)}원 이하로만 구매가 가능합니다."
        private val ERROR_LESS_THAN_MIN_PURCHASE_AMOUNT = "${decimalFormat.format(MIN_PRICE_AMOUNT)}원 이상의 금액을 지불해야 합니다."
    }
}
