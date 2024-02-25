package lotto.model

object LottoMachine {
    private const val PRICE_ERROR_MESSAGE = "구입 금액은 로또 1장의 가격 이상이여야합니다."

    fun createLottos(
        buyLottoPrice: Int,
        lottoNumbersGenerator: LottoNumbersGenerator,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): List<Lotto> {
        require(buyLottoPrice >= lottoPrice) { PRICE_ERROR_MESSAGE }
        val count = buyLottoPrice / lottoPrice
        return List(count) { createLotto(lottoNumbersGenerator) }
    }

    private fun createLotto(lottoNumbersGenerator: LottoNumbersGenerator): Lotto = Lotto(lottoNumbersGenerator.generate())
}
