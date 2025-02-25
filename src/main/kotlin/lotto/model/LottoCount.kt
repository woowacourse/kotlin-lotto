package lotto.model

class LottoCount(
    val number: Int,
) {
    fun isAvailPurchaseLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number

    fun minus(lottoCount: LottoCount): LottoCount {
        require(isAvailPurchaseLottoCount(lottoCount)) { ERROR_EXCEED_LOTTO_COUNT }
        return LottoCount(this.number - lottoCount.number)
    }

    companion object {
        private const val ERROR_EXCEED_LOTTO_COUNT = "구매 가능한 수량을 초과하였습니다."
    }
}
