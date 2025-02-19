package lotto

class PurchaseAmount(
    private val money: Int,
) {
    init {
        require(money % Lotto.PRICE == NO_REMAINDER) { ERROR_EXIST_REMAINDER }
    }

    companion object {
        private const val NO_REMAINDER = 0
        private const val ERROR_EXIST_REMAINDER = "구입 금액은 1,000원 단위입니다."
    }
}
