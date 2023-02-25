package domain

class Ticket(private val lottos: List<Lotto>) : Cloneable {
    val size: Int
        get() = lottos.size

    fun matchTicketCount(winningLotto: WinningLotto): Map<Rank, Int> {
        return lottos.asSequence()
            .map { winningLotto.matchResult(it) }
            .groupingBy { it }
            .eachCount()
    }

    fun <R> map(transform: (Lotto) -> R): List<R> {
        return lottos.map(transform)
    }

    fun forEach(action: (Lotto) -> Unit) {
        lottos.forEach(action)
    }

    public override fun clone(): Ticket {
        return Ticket(lottos.map { it.clone() }.toList())
    }

    operator fun plus(ticket: Ticket) = Ticket(lottos.toList() + ticket.lottos.toList())
    operator fun get(index: Int): Lotto = lottos[index]
}
