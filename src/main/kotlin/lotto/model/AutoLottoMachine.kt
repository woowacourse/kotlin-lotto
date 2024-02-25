package lotto.model

object AutoLottoMachine {
    fun createLottos(
        lottoBuyBudget: LottoBuyBudget,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val autoLottoBuyCount = lottoBuyBudget.getBuyableLottoCount()
        lottoBuyBudget.subtractAvailableFunds(lottoBuyBudget.pricePerLotto * autoLottoBuyCount)
        return List(autoLottoBuyCount) { createLotto(lottoNumbersGenerator) }
    }

    private fun createLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
