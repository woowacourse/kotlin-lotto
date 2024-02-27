package model

@JvmInline
value class LottoCount(val amount: Int) {
    init {
        require(amount > 0) { "amount 는 0보다 커야함" }
    }
}
