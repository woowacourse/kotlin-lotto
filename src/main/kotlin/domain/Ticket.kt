package domain

class Ticket(lottos: List<Lotto>) {
    private val _lottos = lottos.toList()
    val lottos: List<Lotto>
        get() = _lottos.toList()
}
