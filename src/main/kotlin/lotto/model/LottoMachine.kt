package lotto.model

object LottoMachine {
    fun createLottos(
        numberOfLottos: Int,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        return lottoNumbersGenerator.generate(numberOfLottos)
            .map { Lotto(it) }
    }
}
