package domain

class Ticket(lottos: List<Lotto>) {
    private val _lottos = lottos.toList()
    val lottos: List<Lotto>
        get() = _lottos.toList()

    fun matchTicketCount(winningLotto: WinningLotto): Map<Rank, Int> {
        return lottos.asSequence()
            .map { winningLotto.matchResult(it) }
            .groupingBy { it }
            .eachCount()
    }
}
