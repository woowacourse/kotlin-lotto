package lotto.model

class LottoGenerator(private val numberGenerator: LottoNumberGenerator) {
    fun generateLotto(): Lotto {
        return numberGenerator.generateNumbers()
    }
}

interface LottoNumberGenerator {
    fun generateNumbers(): Lotto
}
