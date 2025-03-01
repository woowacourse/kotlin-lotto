package lotto.model

data class LottoCount(
    val number: Int,
) {
    init {
        require(number >= 0) { ERROR_INVALID_LOTTO_COUNT }
    }

    fun minus(lottoCount: LottoCount): LottoCount {
        val number = (this.number - lottoCount.number).coerceAtLeast(0)
        return LottoCount(number)
    }

    fun isPurchasableLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number

    companion object {
        private const val ERROR_INVALID_LOTTO_COUNT = "로또 수량은 0보다 크거나 같아야 합니다."
    }
}
