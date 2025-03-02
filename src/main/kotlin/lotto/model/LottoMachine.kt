package lotto.model

class LottoMachine {
    fun createLottos(
        lottoCount: LottoCount,
        numberGenerator: LottoNumbersGenerator,
    ): List<Lotto> = List(lottoCount.count) { Lotto.from(numberGenerator.generate()) }
}
