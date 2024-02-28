package lotto.model

data class Money(val amount: Long) {
    init {
        require(amount >= MINIMUM_AMOUNT) { "${amount}원은 안됩니다. ${MINIMUM_AMOUNT}원 이상이어야 합니다." }
    }

    fun calculateMargin(prize: Money): Margin {
        return Margin(prize.amount * 1000 / amount / 1000.toDouble())
    }

    operator fun compareTo(other: Money): Int {
        return this.amount.compareTo(other.amount)
    }

    companion object {
        private const val MINIMUM_AMOUNT = 0L
    }
}
