package domain

class PurchaseLottoMoney(val money: Int) {
    val purchaseCount: Int
        get() = money / 1000

    init {
        require(money >= 1000) { "로또를 구입하기 위해서는 최소한 1000원 이상의 금액이여야 합니다." }
    }
}
