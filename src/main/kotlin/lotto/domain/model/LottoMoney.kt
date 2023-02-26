package lotto.domain.model

class LottoMoney(val amount: Int) {

    init {
        require(amount >= MONEY_UNIT) { MONEY_MINIMUM_ERROR }
    }

    companion object {
        const val MONEY_MINIMUM_ERROR = "최소 금액은 1000원입니다."
        const val MONEY_UNIT = 1000
    }
}
