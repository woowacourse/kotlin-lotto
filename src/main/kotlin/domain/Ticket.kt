package domain

class Ticket(private val lottos: List<Lotto>) : List<Lotto> by lottos {
    fun concatenateTicket(ticket: Ticket): Ticket {
        return Ticket(lottos + ticket.lottos)
    }
}
