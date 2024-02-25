package lotto.model

object LottoMachine {
    private const val PRICE_ERROR_MESSAGE = "구입 금액은 로또 1장의 가격 이상이여야합니다."

    fun createAutoLottos(
        lottoBuyPrice: Int,
        lottoNumbersGenerator: LottoNumbersGenerator,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): List<Lotto> {
        require(lottoBuyPrice >= lottoPrice) { PRICE_ERROR_MESSAGE }
        val count = lottoBuyPrice / lottoPrice
        return List(count) { createAutoLotto(lottoNumbersGenerator) }
    }

    private fun createAutoLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
