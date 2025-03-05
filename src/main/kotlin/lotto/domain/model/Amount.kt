package lotto.domain.model

class Amount private constructor(val money: Int) {
    init {
        require(money >= 0) { "[ERROR] 금액은 0 이상이어야 합니다." }
    }

    fun getCount(lottoPrize: Int): Int = money / lottoPrize

    fun paymentOrNull(payMoney: Int): Amount? =
        runCatching { Amount(money - payMoney) }.getOrNull()

    companion object {
        fun valueOf(money: Int): Amount = Amount(money)

        fun valueOfOrNull(money: Int): Amount? =
            runCatching { valueOf(money) }.getOrNull()
    }
}
