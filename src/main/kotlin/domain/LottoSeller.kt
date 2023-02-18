package domain

class LottoSeller(private val lottoMachine: LottoMachine = RandomLottoMachine()) {
    fun sellLottos(count: Int): Ticket {
        return Ticket(List(count) { lottoMachine.create() })
    }
}
