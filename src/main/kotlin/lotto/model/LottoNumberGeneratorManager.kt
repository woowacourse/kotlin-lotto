package lotto.model

object LottoNumberGeneratorManager {
    var generator: NumberGenerator = LottoNumberGenerator()

    fun generate(
        size: Int,
        minNumber: Int,
        maxNumber: Int,
    ): Lotto = generator.generate(size, minNumber, maxNumber)
}
