package lotto.model

object AutoLottoMachine {
    fun createLottos(
        lottoBuyBudget: LottoBuyBudget,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val lottoBuyCount = lottoBuyBudget.getBuyableLottoCount()
        lottoBuyBudget.subtractAvailableFunds(lottoBuyBudget.pricePerLotto * lottoBuyCount)
        return List(lottoBuyCount) { createLotto(lottoNumbersGenerator) }
    }

    private fun createLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
