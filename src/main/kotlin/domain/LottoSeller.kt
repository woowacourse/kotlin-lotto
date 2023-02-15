package domain

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate())
    }
}
