package lottogame.model

@JvmInline
value class LottoCount private constructor(val amount: Int) {
    init {
        require(amount >= MIN_COUNT) { EXCEPTION_COUNT_AMOUNT }
    }

    companion object {
        private const val MIN_COUNT = 0
        private const val EXCEPTION_COUNT_AMOUNT = "count 는 0 이상 이어야함"
        private const val EXCEPTION_COUNT_CALCULATE = "lottoPrice * count = %s 는 cost = %s 보다 클 수 없다 "

        @JvmStatic
        fun from(count: Int): LottoCount {
            return LottoCount(count)
        }

        @JvmStatic
        fun fromNullable(count: Int): LottoCount? {
            if (count < MIN_COUNT) return null
            return LottoCount(count)
        }

        @JvmStatic
        fun of(
            count: LottoCount,
            lottoPrice: Money,
            cost: Money,
        ): LottoCount {
            // 몫
            val limitCount = cost / lottoPrice
            val canBuyCount = count.amount <= limitCount
            check(canBuyCount) { EXCEPTION_COUNT_CALCULATE }
            return LottoCount(count.amount)
        }

        @JvmStatic
        fun ofNullable(
            count: LottoCount,
            lottoPrice: Money,
            cost: Money,
        ): LottoCount? {
            val limitCount = cost / lottoPrice
            val canBuyCount = count.amount <= limitCount
            if (canBuyCount.not()) return null
            return LottoCount(count.amount)
        }
    }
}
