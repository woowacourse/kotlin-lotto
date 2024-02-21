package lotto.model

class LottoMachine(private val price: String) {
    init {
        require(price.toIntOrNull()?.let { it >= MIN_PRICE } == true) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos() =
        List(getLottoTicket()) {
            Lotto(
                (LOTTO_NUMBER_RANGE).shuffled().take(LOTTO_SIZE).sorted()
                    .map { LottoNumber(it.toString()) },
            )
        }

    fun getLottoTicket(): Int = price.toInt() / MIN_PRICE

    companion object {
        const val LOTTO_SIZE = 6
        val LOTTO_NUMBER_RANGE: IntRange = 1..45
        const val MIN_PRICE = 1_000

        const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
    }
}
