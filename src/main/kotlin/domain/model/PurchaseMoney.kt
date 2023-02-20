package domain.model

class PurchaseMoney(
    val money: Int,
) {

    init {
        require(money > ZERO) {
            MONEY_RANGE_ERROR
        }
        require(money.validateMoneyUnit()) {
            MONEY_UNIT_ERROR
        }
    }

    private fun Int.validateMoneyUnit() = this % LOTTO_PRICE == ZERO

    fun getLottosCountToPurchase(): Int = money / LOTTO_PRICE

    companion object {
        private const val ZERO = 0
        private const val LOTTO_PRICE = 1000

        private const val MONEY_UNIT_ERROR = "[ERROR] 천원 단위의 돈을 입력해주세요."
        private const val MONEY_RANGE_ERROR = "[ERROR] 구입 금액은 0원보다 커야합니다."
    }
}
