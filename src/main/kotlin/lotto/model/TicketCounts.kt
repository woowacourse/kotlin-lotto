package lotto.model

class TicketCounts(
    val numberOfTickets: NumberOfTickets,
    val numberOfManual: NumberOfManual,
) {
    init {
        require(numberOfTickets.counts >= numberOfManual.counts) { "발행할 수 있는 로또 개수를 초과했습니다." }
    }
}
