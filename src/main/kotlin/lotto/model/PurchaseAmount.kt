package lotto.model

class PurchaseAmount(private val money: Int) {
    init {
        require(money >= TICKET_PRICE) { "구입 단위는 ${TICKET_PRICE}원 이상이어야 합니다." }
    }

    fun getTotalNumberOfLotto(): Int = money / TICKET_PRICE

    fun toDouble(): Double = money.toDouble()

    companion object {
        const val TICKET_PRICE = 1_000
    }
}
