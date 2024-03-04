package lottogame.model

@JvmInline
value class LottoCount(val amount: Int) {
    init {
        require(amount >= MIN_COUNT) { EXCEPTION_COUNT_AMOUNT }
    }

    constructor(
        count: LottoCount,
        lottoPrice: Money,
        cost: Money,
    ) : this(count.amount) {
        val limitCount = cost / lottoPrice
        val canBuyCount = count.amount <= limitCount
        check(canBuyCount) { EXCEPTION_COUNT_CALCULATE }
    }

    companion object {
        private const val MIN_COUNT = 0
        private const val EXCEPTION_COUNT_AMOUNT = "count 는 0 이상 이어야함"
        private const val EXCEPTION_COUNT_CALCULATE = "lottoPrice * count = %s 는 cost = %s 보다 클 수 없다 "
    }
}
