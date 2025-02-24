package lotto.domain

class LottoPurchaseAmount(val money: Int, lottoPrice: Int) {
    var purchasableCount: Int

    init {
        require(money % lottoPrice == REMAINING) { ERROR_NOT_THOUSAND_UNIT }
        require(money > SMALL_CHANGE) { ERROR_NOT_NEGATIVE_NUMBER }

        purchasableCount = money / lottoPrice
    }

    companion object {
        private const val ERROR_NOT_THOUSAND_UNIT = "[ERROR] 구매 가격이 1000 단위가 아닙니다."
        private const val ERROR_NOT_NEGATIVE_NUMBER = "[ERROR] 구매 가격은 양수여야 합니다."

        private const val REMAINING = 0
        private const val SMALL_CHANGE = 0
    }
}
