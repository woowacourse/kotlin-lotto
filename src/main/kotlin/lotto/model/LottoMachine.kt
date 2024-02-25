package lotto.model

object LottoMachine {
    fun createAutoLottos(
        lottoBuyPrice: LottoBuyPrice,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val count = lottoBuyPrice.getBuyCount()
        return List(count) { createAutoLotto(lottoNumbersGenerator) }
    }

    private fun createAutoLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
