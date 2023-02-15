package domain

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate())
    }

    fun sellLottos(count: Int): Ticket {
        return Ticket(List(count) { Lotto(numberGenerator.generate()) })
    }
}
