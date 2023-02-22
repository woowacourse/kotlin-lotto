package domain

class LottoSeller(private val lottoMachine: LottoMachine = LottoVendingMachine()) {
    fun sellRandomLottos(count: Int): Ticket {
        return Ticket(List(count) { lottoMachine.createRandomLotto() })
    }

    private fun sellManualLotto(numbers: Set<Int>): Lotto {
        return lottoMachine.createManualLotto(numbers)
    }

    fun sellManualLottos(lottoNumbers: List<Set<Int>>): Ticket {
        return Ticket(lottoNumbers.map { sellManualLotto(it) })
    }
}
