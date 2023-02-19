package domain

class Ticket(val lottos: List<Lotto>) : List<Lotto> by lottos
