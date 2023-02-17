package domain

class PurchaseLottoMoney(val money: Int) {
    val purchaseCount: Int
        get() = money / ONE_LOTTO_MONEY

    init {
        require(money >= ONE_LOTTO_MONEY) { ERROR_LOTTO_PURCHASE_MONEY }
    }

    companion object {
        const val ONE_LOTTO_MONEY = 1000
        const val ERROR_LOTTO_PURCHASE_MONEY = "로또를 구입하기 위해서는 최소한 ${ONE_LOTTO_MONEY}원 이상의 금액이여야 합니다."
    }
}
