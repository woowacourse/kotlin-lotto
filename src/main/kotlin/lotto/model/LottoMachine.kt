package lotto.model

class LottoMachine(private val price: Int, private val lottoNumberGenerator: LottoNumberGenerator) {
    init {
        require(price >= MIN_LOTTO_PURCHASE_PRICE) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos(): List<Lotto> {
        val count = price / MIN_LOTTO_PURCHASE_PRICE
        return List(count) { createLotto() }
    }

    private fun createLotto(): Lotto = Lotto(lottoNumberGenerator.generate())

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
        private const val MIN_LOTTO_PURCHASE_PRICE = 1000
    }
}
