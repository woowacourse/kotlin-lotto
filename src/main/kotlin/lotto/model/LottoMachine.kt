package lotto.model

object LottoMachine {
    fun createLottos(
        lottoBuyPrice: LottoBuyPrice,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val count = lottoBuyPrice.getBuyCount()
        return List(count) { createLotto(lottoNumbersGenerator) }
    }

    private fun createLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
