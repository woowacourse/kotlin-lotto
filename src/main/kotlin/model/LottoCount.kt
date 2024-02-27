package model

@JvmInline
value class LottoCount(val amount: Int) {
    init {
        require(amount > 0) { "amount 는 0보다 커야함" }
    }

    operator fun compareTo(o: LottoCount) = amount - o.amount

    operator fun plus(o: LottoCount) = LottoCount(amount + o.amount)

    operator fun minus(o: LottoCount) = LottoCount(amount - o.amount)

    companion object {
        val MAX = LottoCount(Int.MAX_VALUE)
    }
}
