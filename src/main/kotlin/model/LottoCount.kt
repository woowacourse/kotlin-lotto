package model

@JvmInline
value class LottoCount private constructor(val amount: Int) {
    init {
        require(amount > 0) { "amount 는 0보다 커야함" }
    }

    companion object {
        @JvmStatic
        fun of(
            count: Int,
            lottoPrice: Money,
            cost: Money,
        ): LottoCount {
            val totalLottiePrice = lottoPrice * count
            check(cost >= (totalLottiePrice)) { "lottoPrice * count = $totalLottiePrice 는 cost = $cost 보다 클 수 없다 " }
            return LottoCount(cost / totalLottiePrice)
        }
    }
}
