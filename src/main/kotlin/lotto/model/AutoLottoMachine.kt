package lotto.model

object AutoLottoMachine {
    fun createLottos(
        lottoBuyBudget: LottoBuyBudget,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val autoLottoBuyCount = lottoBuyBudget.getBuyableLottoCount()
        return lottoNumbersGenerator.generate(autoLottoBuyCount).map { Lotto(it) }.also {
            lottoBuyBudget.subtractAvailableFunds(lottoBuyBudget.pricePerLotto * autoLottoBuyCount)
        }
    }
}
