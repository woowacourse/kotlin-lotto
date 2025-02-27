package lotto.model

data class LottoCount(
    val number: Int,
) {
    init {
        require(number >= 0) { ERROR_NEGATIVE_COUNT }
    }

    fun isPositive(): Boolean = number > 0

    fun minus(lottoCount: LottoCount): LottoCount = LottoCount(this.number - lottoCount.number)

    fun isPurchasableLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number

    companion object {
        private const val ERROR_NEGATIVE_COUNT = "수량은 음수가 될 수 없습니다."
    }
}
