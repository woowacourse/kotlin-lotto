package lotto.model

class LottoMachine(private val price: String) {
    init {
        require(price.toIntOrNull()?.let { it >= MIN_PRICE } == true) { PRICE_ERROR_MESSAGE }
    }

    fun createLottoBundle(): LottoBundle {
        val lottos =
            List(getLottoTicket()) {
                Lotto((LOTTO_NUMBER_RANGE).shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it.toString()) })
            }

        return LottoBundle(lottos)
    }

    fun getLottoTicket(): Int = price.toInt() / MIN_PRICE

    companion object {
        private const val LOTTO_SIZE = 6
        private val LOTTO_NUMBER_RANGE: IntRange = 1..45
        private const val MIN_PRICE = 1_000

        private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
    }
}
