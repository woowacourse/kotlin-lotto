package lotto.model

object LottoMachine {
    private const val LOTTO_SIZE = 6
    private val LOTTO_NUMBER_RANGE: IntRange = 1..45
    private const val MIN_PRICE = 1_000

    private const val PRICE_TYPE_ERROR_MESSAGE = "구입 금액은 자연수여야 합니다."
    private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."

    fun createLottoBundle(price: String): LottoBundle {
        require(price.toIntOrNull() != null) { PRICE_TYPE_ERROR_MESSAGE }
        require(price.toIntOrNull()?.let { it >= MIN_PRICE } == true) { PRICE_ERROR_MESSAGE }

        val lottos = List(getNumberOfLottoTickets(price)) { randomLotto() }

        return LottoBundle(lottos)
    }

    private fun randomLotto(): Lotto = Lotto(LOTTO_NUMBER_RANGE.shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) }.toSet())

    fun getNumberOfLottoTickets(price: String): Int = price.toInt() / MIN_PRICE
}
