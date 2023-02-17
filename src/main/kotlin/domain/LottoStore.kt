package domain

class LottoStore(
    private val lottoGenerator: LottoGenerator,
) {

    fun buyLotto(amount: Int): List<Lotto> = LottoFactory(lottoGenerator).create(getCount(Amount.from(amount)))

    private fun getCount(amount: Amount): Int = amount.value / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
