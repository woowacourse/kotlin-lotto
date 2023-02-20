package domain

class Payment(val amount: Long) {

    init {
        require((amount % 1000L == 0L) and (amount != 0L)) { "[Error] $amount 받은 돈이 1000원 단위여야합니다." }
    }

    constructor(amount: Int) : this(amount.toLong())

    fun calculateLottoCount(): Int {
        return (amount / LOTTO_PRICE).toInt()
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
