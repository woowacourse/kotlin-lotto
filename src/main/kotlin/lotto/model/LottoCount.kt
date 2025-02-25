package lotto.model

data class LottoCount(
    val number: Int,
) {
    init {
        require(number >= 0) { ERROR_NEGATIVE_COUNT }
    }

    fun validateLottoCount(lottoCount: LottoCount) {
        require(isAvailPurchaseLottoCount(lottoCount)) { ERROR_EXCEED_LOTTO_COUNT }
    }

    fun minus(lottoCount: LottoCount): LottoCount = LottoCount(this.number - lottoCount.number)

    private fun isAvailPurchaseLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number

    companion object {
        private const val ERROR_EXCEED_LOTTO_COUNT = "구매 가능한 수량을 초과하였습니다."
        private const val ERROR_NEGATIVE_COUNT = "수량은 음수가 될 수 없습니다."
    }
}
