package lotto.model

class LottoPurchaseInfo(
    val amount: Int,
    val manualLottoCount: Int,
) {
    init {
        require(amount >= LOTTO_PRICE) { MONEY_UNDER_MIN }
        require(amount % LOTTO_PRICE == 0) { MONEY_UNIT_MESSAGE }
    }

    fun getTotalLottoQuantity(): Int = amount / LOTTO_PRICE

    fun getAutoLottoQuantity(): Int = getTotalLottoQuantity() - manualLottoCount

    companion object {
        const val LOTTO_PRICE = 1000
        private const val MONEY_UNIT_MESSAGE = "[ERROR] 구입 금액은 천원 단위여야 합니다."
        private const val MONEY_UNDER_MIN = "[ERROR] 구입 금액이 최소 금액보다 작습니다."
    }
}
