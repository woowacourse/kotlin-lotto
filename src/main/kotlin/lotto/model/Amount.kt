package lotto.model

class Amount(val money: Int) {
    init {
        require(money >= LOTTO_PRICE) { MONEY_UNDER_MIN }
        require(money % LOTTO_PRICE == 0) { MONEY_UNIT_MESSAGE }
    }

    fun getLottoQuantity(): Int = money / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
        const val MONEY_UNIT_MESSAGE = "[ERROR] 구입 금액은 천원 단위여야 합니다."
        const val MONEY_UNDER_MIN = "[ERROR] 구입 금액이 최소 금액보다 작습니다."
    }
}
