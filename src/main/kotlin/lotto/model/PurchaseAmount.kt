package lotto.model

data class PurchaseAmount(val money: Int, val numberOfManualTickets: Int) {
    private val totalNumberOfLotto = money / TICKET_PRICE

    init {
        require(money >= TICKET_PRICE) { "구입 단위는 ${TICKET_PRICE}원 이상이어야 합니다." }
        require(numberOfManualTickets <= totalNumberOfLotto) { "수동으로 발행 가능한 로또 개수는 총 로또 개수를 넘을 수 없습니다." }
    }

    fun getNumberOfAutoTickets(): Int = totalNumberOfLotto - numberOfManualTickets

    companion object {
        const val TICKET_PRICE = 1_000
    }
}
