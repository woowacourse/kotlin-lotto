package lotto.model

class ManualLottoMachine(private val manualLottoBuyCount: Int, private val lottoBuyBudget: LottoBuyBudget) {
    init {
        require(manualLottoBuyCount <= lottoBuyBudget.getBuyableLottoCount()) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos(lottoNumbersGenerator: LottoNumbersGenerator): List<Lotto> {
        return lottoNumbersGenerator.generate(manualLottoBuyCount).map { Lotto(it) }.also {
            lottoBuyBudget.subtractAvailableFunds(manualLottoBuyCount * lottoBuyBudget.pricePerLotto)
        }
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "수동으로 구입하는 로또의 가격은 로또 구입 가격이하여야 합니다."
    }
}
