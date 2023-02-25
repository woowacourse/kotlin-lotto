package domain

class LottoSeller(private val lottoMachine: LottoMachine = RandomLottoMachine()) {
    private fun sellManualLotto(numbers: Set<Int>): Lotto {
        return Lotto(numbers.map(::LottoNumber).toSet())
    }

    fun sellManualLottos(lottoNumbers: List<Set<Int>>): Ticket {
        return Ticket(lottoNumbers.map { sellManualLotto(it) })
    }

    fun sellRandomLottos(count: Int): Ticket {
        return Ticket(List(count) { lottoMachine.create() })
    }
}
