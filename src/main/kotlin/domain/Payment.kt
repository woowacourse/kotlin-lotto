package domain

class Payment(val amount: Long) {

    init {
        require((amount % 1000L == 0L) and (amount != 0L)) { "[Error] $amount 받은 돈이 1000원 단위여야합니다." }
    }

    constructor(amount: String) : this(
        amount.toLongOrNull() ?: throw NumberFormatException("[Error] 1000 단위의 숫자로 값을 입력해주세요.")
    )

    fun calculateMaxLottoCount(): Int {
        return (amount / LOTTO_PRICE).toInt()
    }

    fun calculateAutoLottoCount(maxLottoCount: Int, manualCount: Int): Int {
        return maxLottoCount - manualCount
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
