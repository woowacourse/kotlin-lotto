package domain

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate())
    }

    fun sellLottos(count: Int): List<Lotto> {
        return List<Lotto>(count) { Lotto(numberGenerator.generate()) }
    }
}
