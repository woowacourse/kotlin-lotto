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
        return LottoFactory(lottoGenerator).createLottos(getCount(money))
    }

    private fun getCount(money: Money): Int = money.value / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
        private const val ERROR_INVALID_COUNT = "구입 금액보다 많은 로또를 구매할 수 없습니다.\n잘못된 값: %d"
        private const val ERROR_INVALID_MONEY = "최소 금액보다 적은 돈으로 로또를 구매할 수 없습니다.\n잘못된 값: %d"
    }
}
