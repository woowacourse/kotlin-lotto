package lotto.domain.model

import lotto.Constants

class PurchaseAmount(
    private var amount: Int,
) {
    init {
        require(amount >= Constants.LOTTO_AMOUNT) { ERROR_INVALID_MINIMUM_AMOUNT }
    }

    fun calculatePurchaseLottoCount(): Int = amount / Constants.LOTTO_AMOUNT

    companion object {
        private const val ERROR_INVALID_MINIMUM_AMOUNT = "구입금액은 로또 1장 가격(1000원)보다 커야 합니다."
    }
}
