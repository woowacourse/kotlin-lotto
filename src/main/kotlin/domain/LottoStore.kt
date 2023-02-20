package domain

class LottoStore(
    private val lottoGenerator: LottoGenerator,
) {
    val lottoPrice: Int
        get() = LOTTO_PRICE

    fun buyManualLotto(money: Money, vararg lottos: Lotto): List<Lotto> {
        require(money.value >= LOTTO_PRICE * lottos.size) { ERROR_INVALID_COUNT.format(money.value) }
        return lottos.toList()
    }

    fun buyAutoLotto(money: Money): List<Lotto> {
        require(money.value >= LOTTO_PRICE) { ERROR_INVALID_MONEY.format(money.value) }
        return createLottos(getCount(money))
    }

    fun createLottos(count: Int): List<Lotto> {
        require(count in MINIMUM_COUNT..MAXIMUM_COUNT) { ERROR_CREATE_COUNT.format(count) }
        return List(count) { createLotto() }
    }

    private fun createLotto(): Lotto = lottoGenerator.generateLotto()

    private fun getCount(money: Money): Int = money.value / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
        private const val MINIMUM_COUNT = 1
        private const val MAXIMUM_COUNT = 100
        private const val ERROR_INVALID_COUNT = "구입 금액보다 많은 로또를 구매할 수 없습니다.\n잘못된 값: %d"
        private const val ERROR_INVALID_MONEY = "최소 금액보다 적은 돈으로 로또를 구매할 수 없습니다.\n잘못된 값: %d"
        private const val ERROR_CREATE_COUNT = "한 번에 생성할 수 있는 로또 개수는 1개 이상 100개 이하입니다.\n잘못된 값: %d"
    }
}
