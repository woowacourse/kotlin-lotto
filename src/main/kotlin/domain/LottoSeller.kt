package domain

class LottoSeller() {
    fun sellTicket(count: Int, lottoMachine: LottoMachine): Ticket {
        return Ticket(lottoMachine.create(count))
    }
}
