package lotto.model

class LottoMachine(private val price: Int) {
    init {
        require(price >= LOTTO_PRICE) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos(): List<Lotto> {
        val count = price / LOTTO_PRICE
        return List(count) {
            createLotto()
        }
    }

    private fun createLotto() =
        Lotto(
            (LOTTO_NUMBER_RANGE).shuffled().take(LOTTO_SIZE).sorted()
                .map { LottoNumber(it) },
        )

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."

        private const val LOTTO_SIZE = 6
        private const val LOTTO_PRICE = 1000
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        private val LOTTO_NUMBER_RANGE = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
    }
}
