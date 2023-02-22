package domain

class LottoSeller(private val lottoMachine: LottoMachine = LottoVendingMachine()) {
    fun sellRandomLottos(count: Int): Ticket {
        return Ticket(List(count) { lottoMachine.createRandomLotto() })
    }
}
