package model

class LottoCountCalculator(private val lottoPrice: Money) {
    fun calculate(
        limit: LottoCount = LottoCount.MAX,
        cost: Money,
    ): LottoCount {
        val count = LottoCount(cost / lottoPrice)
        check(limit > count) { "count=$count 는 limit=$limit 보다 작아야합니다." }
        return count
    }
}
