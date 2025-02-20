package lotto.domain.value

import lotto.constants.LottoConstants.LOTTO_PRICE

class PurchaseAmount(
    val amount: Int,
) {
    init {
        require(amount % LOTTO_PRICE == 0) {
            INDIVISIBLE_PURCHASE_AMOUNT_VALUE_ERROR.format(
                amount,
            )
        }
        require(amount >= LOTTO_PRICE) {
            PURCHASE_AMOUNT_LESS_THAN_MINIMUM.format(
                amount,
            )
        }
    }

    companion object {
        private const val INDIVISIBLE_PURCHASE_AMOUNT_VALUE_ERROR = "구매 금액 %d는 로또 가격 ${LOTTO_PRICE}으로 나눌 수 없습니다."
        private const val PURCHASE_AMOUNT_LESS_THAN_MINIMUM =
            "최소 구매 금액인 ${LOTTO_PRICE}이상의 값을 입력해야 합니다. (입력값: %d)"
    }
}
