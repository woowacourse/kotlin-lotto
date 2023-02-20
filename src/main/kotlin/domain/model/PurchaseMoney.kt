package domain.model

class PurchaseMoney(
    val money: Int
) {
    init {
        require(money > ZERO) {
            MONEY_RANGE_ERROR
        }
        require(this.money % LOTTO_PRICE == ZERO) {
            NUMBER_UNIT_ERROR
        }
    }

    companion object {
        private const val ZERO = 0
        private const val LOTTO_PRICE = 1000
        private const val MONEY_RANGE_ERROR = "[ERROR] 구입 금액은 0원보다 커야합니다."
        private const val NUMBER_UNIT_ERROR = "[ERROR] 천원 단위로 입력해주세요."
    }
}
