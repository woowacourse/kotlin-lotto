package lottogame.model

@JvmInline
value class LottoCount private constructor(val amount: Int) {
    init {
        require(amount > 0) { EXCEPTION_COUNT_AMOUNT }
    }

    companion object {
        private const val EXCEPTION_COUNT_AMOUNT = "count 는 0보다 커야함"
        private const val EXCEPTION_COUNT_CALCULATE = "lottoPrice * count = %s 는 cost = %s 보다 클 수 없다 "

        @JvmStatic
        fun createOrNull(
            count: Count,
            lottoPrice: Money,
            cost: Money,
        ): LottoCount? {
            val totalLottiePrice = lottoPrice * count.amount
            val isCostInsufficient = cost < totalLottiePrice
            if (isCostInsufficient) return null
            return LottoCount(cost / totalLottiePrice)
        }
    }
}
