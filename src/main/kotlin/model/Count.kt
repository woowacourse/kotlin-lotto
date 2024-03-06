package model

@JvmInline
value class Count private constructor(val amount: Int) {
    fun getAutoCount(money: Money): Count = Count((money.amount.toInt() - amount * LOTTO_PRICE) / LOTTO_PRICE)

    companion object {
        private const val LOTTO_PRICE: Int = 1000

        private const val ERROR_LESS_THAN_ZERO = "0 이상의 값을 입력해주셔야 합니다."
        private const val ERROR_UNAFFORDABLE = "구입금액으로 구매 불가능한 갯수입니다."

        fun from(
            amount: Int,
            money: Money,
        ): Count {
            require(amount >= 0) { ERROR_LESS_THAN_ZERO }
            require(money.amount.toInt() >= amount * LOTTO_PRICE) { ERROR_UNAFFORDABLE }

            return Count(amount)
        }
    }
}
