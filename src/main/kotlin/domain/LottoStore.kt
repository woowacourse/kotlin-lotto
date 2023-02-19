package domain

class LottoStore(
    private val lottoGenerator: LottoGenerator,
) {

    fun buyLotto(money: Money): List<Lotto> = LottoFactory(lottoGenerator).create(getCount(money))

    private fun getCount(money: Money): Int = money.value / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
