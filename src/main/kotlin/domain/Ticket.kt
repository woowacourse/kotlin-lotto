package domain

class Ticket(lottos: List<Lotto>) {
    private val _lottos = lottos.toList()
    val lottos: List<Lotto>
        get() = _lottos.toList()

    val size: Int
        get() = lottos.size

    fun matchTicketCount(winningLotto: WinningLotto): Map<Rank, Int> {
        return lottos.asSequence()
            .map { winningLotto.matchResult(it) }
            .groupingBy { it }
            .eachCount()
    }

    fun copy() = Ticket(lottos.toList())
    operator fun plus(ticket: Ticket) = Ticket(lottos.toList() + ticket.lottos.toList())
    operator fun get(index: Int): Lotto = lottos[index]
}
