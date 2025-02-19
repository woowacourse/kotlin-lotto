package lotto

class LottoPurchaseAmount(
    private val money: Int,
) {
    init {
        require(money in MIN_LOTTO_PURCHASE_AMOUNT..MAX_LOTTO_PURCHASE_AMOUNT) { ERROR_OUT_OF_RANGE_LOTTO_PURCHASE_AMOUNT }
        require(money % LOTTO_PRICE == NO_REMAINDER) { ERROR_EXIST_REMAINDER }
    }

    fun getLottoCount(): Int = money / LOTTO_PRICE

    companion object {
        private const val MIN_LOTTO_PURCHASE_AMOUNT = 1_000
        private const val MAX_LOTTO_PURCHASE_AMOUNT = 100_000
        private const val NO_REMAINDER = 0
        private const val LOTTO_PRICE = 1_000
        private const val ERROR_OUT_OF_RANGE_LOTTO_PURCHASE_AMOUNT = "구입 금액은 최소 1,000원 이상 최대 100,000원 이하 입니다."
        private const val ERROR_EXIST_REMAINDER = "구입 금액은 1,000원 단위입니다."
    }
}
