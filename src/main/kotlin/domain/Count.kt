package domain

class Count(val money: Money, val count: Int) {

    init {
        require(money.getLottoCount() >= count) { LESS_MONEY_ERROR_MESSAGE }
        require(count >= 0) { COUNT_ERROR_MESSAGE }
    }

    fun getAutoLottoCount(): Int = money.getLottoCount() - count

    companion object {
        const val LESS_MONEY_ERROR_MESSAGE = "원하는 수동 로또 수량을 입력된 금액으로 구매할 수 없습니다."
        const val COUNT_ERROR_MESSAGE = "입력값은 0보다 커야합니다."
    }
}
