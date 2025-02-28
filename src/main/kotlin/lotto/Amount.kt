package lotto

class Amount private constructor(
    val money: Int,
) {
    fun getCount(lottoPrize: Int): Int = money / lottoPrize

    fun paymentOrNull(payMoney: Int): Amount? {
        if (money < payMoney) return null
        return Amount(money - payMoney)
    }

    companion object {
        fun createOrNull(input: Int): Amount? {
            if (input < 0) return null
            return Amount(input)
        }
    }
}
