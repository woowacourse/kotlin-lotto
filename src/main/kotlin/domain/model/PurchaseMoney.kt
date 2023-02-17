package domain.model

class PurchaseMoney(
    val money: Int,
) {
    init {
        require(money > ZERO) {
            MONEY_RANGE_ERROR
        }
    }

    companion object {
        private const val ZERO = 0

        private const val MONEY_RANGE_ERROR = "[ERROR] 구입 금액은 0원보다 커야합니다."
    }
}
