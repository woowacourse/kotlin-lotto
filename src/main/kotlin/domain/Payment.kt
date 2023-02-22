package domain

class Payment(nullableAmount: Long?) {
    val amount: Long

    init {
        if (nullableAmount == null) throw NumberFormatException("[Error] 1000 단위의 숫자로 값을 입력해주세요.")
        amount = nullableAmount
        require((amount % 1000L == 0L) and (amount != 0L)) { "[Error] $amount 받은 돈이 1000원 단위여야합니다." }
    }

    constructor(amount: String) : this(amount.toLongOrNull())

    fun calculateLottoCount(): Int {
        return (amount / LOTTO_PRICE).toInt()
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
