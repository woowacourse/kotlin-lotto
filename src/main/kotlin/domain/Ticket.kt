package domain

class Ticket(val lottos: List<Lotto>) {
    fun concatenateTicket(ticket: Ticket): Ticket {
        return Ticket(lottos + ticket.lottos)
    }

    fun size(): Int = lottos.size

    override fun toString(): String {
        var result = ""
        lottos.forEach { lotto ->
            result += lotto.toList().map { it.toInt() }.sorted().toString() + "\n"
        }
        return result
    }
}
