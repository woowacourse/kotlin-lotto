package lotto.model

class LottoPurchaseAmount(
    val money: Int,
) {
    init {
        require(money in MIN_LOTTO_PURCHASE_AMOUNT..MAX_LOTTO_PURCHASE_AMOUNT) { ERROR_OUT_OF_RANGE_LOTTO_PURCHASE_AMOUNT }
    }

    fun getLottoCount(lottoPrice: Int = LOTTO_PRICE): LottoCount = LottoCount(money / lottoPrice)

    companion object {
        private const val MIN_LOTTO_PURCHASE_AMOUNT = 1_000
        private const val MAX_LOTTO_PURCHASE_AMOUNT = 100_000
        private const val LOTTO_PRICE = 1_000
        private const val ERROR_OUT_OF_RANGE_LOTTO_PURCHASE_AMOUNT = "구입 금액은 최소 1,000원 이상 최대 100,000원 이하 입니다."
    }
}
