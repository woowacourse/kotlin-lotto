package lotto.domain.model

@JvmInline
value class Amount(val value: Int) {
    init {
        require(value >= LOTTO_PRICE) { AMOUNT_ERROR }
    }

    fun getQuantity(): Int = value / LOTTO_PRICE

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val AMOUNT_ERROR = "[ERROR] 구입 금액이 최소 금액보다 작습니다."
    }
}
