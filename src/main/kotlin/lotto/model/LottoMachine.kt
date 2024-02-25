package lotto.model

object LottoMachine {
    fun createAutoLottos(
        lottoBuyPrice: Int,
        lottoNumbersGenerator: LottoNumbersGenerator,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): List<Lotto> {
        val count = lottoBuyPrice / lottoPrice
        return List(count) { createAutoLotto(lottoNumbersGenerator) }
    }

    private fun createAutoLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
