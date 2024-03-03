package lotto.model

class NumberOfTickets(cash: Int) {
    val counts: Int

    init {
        require(cash >= TICKET_PRICE) { "구매 가능한 로또의 개수는 최소 1장(1,000원) 이상입니다." }
        counts = cash / TICKET_PRICE
    }

    companion object {
        const val TICKET_PRICE = 1_000
    }
}
