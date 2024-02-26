package lotto.model

object AutoLottoMachine {
    fun createLottos(
        lottoBuyBudget: LottoBuyBudget,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val autoLottoBuyCount = lottoBuyBudget.getBuyableLottoCount()
        lottoBuyBudget.subtractAvailableFunds(lottoBuyBudget.pricePerLotto * autoLottoBuyCount)
        return lottoNumbersGenerator.generate(autoLottoBuyCount).map { Lotto(it) }
    }
}
