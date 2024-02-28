package lotto.model

class PurchaseAmount private constructor(val money: Int) {
    init {
        require(money >= TICKET_PRICE) { "구입 단위는 ${TICKET_PRICE}원 이상이어야 합니다." }
    }

    fun getTotalNumberOfLotto(): Int = money / TICKET_PRICE

    companion object {
        const val TICKET_PRICE = 1_000

        fun from(money: Int): PurchaseAmount? {
            if (money < TICKET_PRICE) {
                return null
            }
            return PurchaseAmount(money)
        }
    }
}
