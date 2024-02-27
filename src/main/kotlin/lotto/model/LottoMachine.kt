package lotto.model

object LottoMachine {
    fun createLottos(
        numberOfLottos: Int,
        lottosGenerator: LottosGenerator,
    ): List<Lotto> {
        return lottosGenerator.generate(numberOfLottos)
    }
}
