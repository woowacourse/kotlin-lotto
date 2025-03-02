package lotto.domain

class LottoFactory {
    fun generateLottos(
        amount: Int,
        generator: NumberGenerator,
    ): List<Lotto> {
        return List(amount) { Lotto(generator.generate()) }
    }
}
