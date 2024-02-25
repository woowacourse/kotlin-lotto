package lotto.model

object AutoLottoMachine {
    fun createLottos(
        lottoBuyBudget: LottoBuyBudget,
        lottoNumbersGenerator: LottoNumbersGenerator,
    ): List<Lotto> {
        val count = lottoBuyBudget.getTotalLottoBuyCount()
        lottoBuyBudget.subtractLottoBuyPrice(lottoBuyBudget.lottoPrice * count)
        return List(count) { createLotto(lottoNumbersGenerator) }
    }

    private fun createLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
